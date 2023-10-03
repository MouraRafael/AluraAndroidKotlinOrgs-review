package br.com.alura.estudo.orgs.ui.activity

import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.estudo.orgs.dao.ProdutosDao
import br.com.alura.estudo.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.alura.estudo.orgs.databinding.FormularioImagemBinding
import br.com.alura.estudo.orgs.model.Produto
import br.com.alura.estudo.orgs.tentaCarregarImagem
import br.com.alura.estudo.orgs.ui.dialog.FormularioImagemDialog
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.imageLoader
import coil.load
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url:String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
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
        /*
                        botalSalvar.setOnClickListener(object : View.OnClickListener {
                            override fun onClick(p0: View?) {
                                val campoNome = findViewById<EditText>(R.id.form_nome)
                                val nome = campoNome.text.toString();
                                val botalSalvar:Button = findViewById(R.id.botao_salvar)
                                Log.i("FormProduto","onCreate $nome")
                            }
                        }) //Listener antigo*/
        val botalSalvar: Button = binding.botaoSalvar
        val dao = ProdutosDao();
        botalSalvar.setOnClickListener {
            val produto = criaProduto()
            dao.adiciona(produto)
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
            nome,
            descricao,
            valor,
            url
        )
    }
}