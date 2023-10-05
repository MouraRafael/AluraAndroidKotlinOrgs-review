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
    private var url:String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        title = "Cadastro de Produtos"

        //Imageloader é usado para dar suporte a gifs animados
        val imageLoader = ImageLoader.Builder(this)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()


        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        binding.activityFormularioProdutoImagem.setOnClickListener {
            FormularioImagemDialog(this).mostra(url){urlImagem->
                url=urlImagem

                binding.activityFormularioProdutoImagem.tentaCarregarImagem(url,imageLoader)

            }
        }

    }

    private fun configuraBotaoSalvar() {
        val botalSalvar: Button = binding.botaoSalvar

        val dao = AppDataBase.instanciar(this).dao()



        botalSalvar.setOnClickListener {
            binding.activityFormularioProdutoProgressBar.show()
            val produto = criaProduto()
            dao.salva(produto)
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
            nome =nome,
            descricao=descricao,
            preco =valor,
            imagem = url
        )
    }
}