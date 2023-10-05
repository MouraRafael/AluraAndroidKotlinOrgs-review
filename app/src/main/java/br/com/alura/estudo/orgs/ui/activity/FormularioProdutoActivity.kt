package br.com.alura.estudo.orgs.ui.activity

import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.estudo.orgs.database.AppDataBase
import br.com.alura.estudo.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.alura.estudo.orgs.model.Produto
import br.com.alura.estudo.orgs.tentaCarregarImagem
import br.com.alura.estudo.orgs.ui.dialog.FormularioImagemDialog
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url: String? = null

    private var idProduto = 0L;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Cadastro de Produtos"

        //Imageloader é usado para dar suporte a gifs animados
        val imageLoader = habilitaGIFs()

        val produto:Produto? =  if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            intent.getParcelableExtra(CHAVE_PRODUTO,Produto::class.java)
        }else{
            intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)
        }

        produto?.let {
            idProduto = it.id
            url = it.imagem
            binding.activityFormularioNome.setText(it.nome)
            binding.activityFormularioDescricao.setText(it.descricao)
            binding.activityFormularioValor.setText(it.preco.toString())
            binding.activityFormularioProdutoImagem.tentaCarregarImagem(it.imagem, imageLoader)

            title = "Editar Produto"

        }



        setContentView(binding.root)
        configuraBotaoSalvar()
        binding.activityFormularioProdutoImagem.setOnClickListener {
            FormularioImagemDialog(this).mostra(url) { urlImagem ->
                url = urlImagem

                binding.activityFormularioProdutoImagem.tentaCarregarImagem(url, imageLoader)

            }
        }

    }

    private fun habilitaGIFs() = ImageLoader.Builder(this)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    private fun configuraBotaoSalvar() {
        val botalSalvar: Button = binding.botaoSalvar

        val dao = AppDataBase.instanciar(this).dao()



        botalSalvar.setOnClickListener {
            binding.activityFormularioProdutoProgressBar.show()
            val produto = criaProduto()
            if (idProduto > 0) {
                dao.atualiza(produto)
            } else {
                dao.salva(produto)

            }
            binding.activityFormularioProdutoProgressBar.hide()
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = binding.activityFormularioNome
        val nome = campoNome.text.toString();
        val campoDescricao = binding.activityFormularioDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor = binding.activityFormularioValor
        val valorEmTexto = campoValor.text.toString()

        val valor = if (valorEmTexto.isNullOrBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produto(
            id = idProduto,
            nome = nome,
            descricao = descricao,
            preco = valor,
            imagem = url
        )
    }
}