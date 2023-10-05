package br.com.alura.estudo.orgs.ui.activity

import android.icu.number.NumberFormatter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.estudo.orgs.R
import br.com.alura.estudo.orgs.database.AppDataBase
import br.com.alura.estudo.orgs.databinding.ActivityProdutosDetalhesBinding
import br.com.alura.estudo.orgs.model.Produto
import br.com.alura.estudo.orgs.tentaCarregarImagem
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import java.text.NumberFormat
import java.util.Locale

private const val TAG = "DetalhesProduto"

class DetalhesProdutoActivity : AppCompatActivity() {

    private lateinit var produto: Produto
    private val binding by lazy {
        ActivityProdutosDetalhesBinding.inflate(layoutInflater)
    }
    val imageLoader by lazy {
        ImageLoader.Builder(this)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        //supportActionBar?.hide()
        intent.getParcelableExtra<Produto>("chave")?.let { produtoCarregado: Produto ->
            produto = produtoCarregado
            preencheCampos(produtoCarregado)
        } ?: finish()


    }

    private fun preencheCampos(produto: Produto) {
        val precoFormatado =
            NumberFormat.getCurrencyInstance(Locale("pt", "br")).format(produto.preco)

        binding.activityDetalhesProdutoValor.text = precoFormatado
        binding.activityDetalhesProdutoTitulo.text = produto.nome
        binding.activityDetalhesProdutoDetalhes.text = produto.descricao
        binding.activityDetalhesProdutoImagem.tentaCarregarImagem(produto.imagem, imageLoader)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        //basicamente, infle o menu com o arquivo de menu e passe para o onCreateOptionsMenu
        menuInflater.inflate(R.menu.menu_editar, menu)
        return super.onCreateOptionsMenu(menu)

    }

    //Filtro para quando os itens do menu forem selecionados
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (::produto.isInitialized) {
            val dao = AppDataBase.instanciar(this).dao()
            when (item.itemId) {
                R.id.menu_detalhes_produto_editar -> {
                    Log.i(TAG, "onOptionsItemSelected Editar")
                }
                R.id.menu_detalhes_produto_excluir -> {
                    dao.remove(produto)
                    finish()
                }
            }

        }


        return super.onOptionsItemSelected(item)
    }
}