package br.com.alura.estudo.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import br.com.alura.estudo.orgs.R
import br.com.alura.estudo.orgs.dao.ProdutosDao
import br.com.alura.estudo.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.alura.estudo.orgs.databinding.FormularioImagemBinding
import br.com.alura.estudo.orgs.model.Produto
import coil.load
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        binding.activityFormularioProdutoImagem.setOnClickListener {
            val bindingDoFormularioImagem = FormularioImagemBinding.inflate(layoutInflater);

            bindingDoFormularioImagem.formularioImagemBotaoCarregar.setOnClickListener {
                url = bindingDoFormularioImagem.formularioImagemUrl.text.toString()
                bindingDoFormularioImagem.formularioImagemImageview.load(url)
            }

            AlertDialog.Builder(this)
                .setView(bindingDoFormularioImagem.root)
                .setPositiveButton("ok", { _, _ ->
                    val  url = bindingDoFormularioImagem.formularioImagemUrl.text.toString()
                    binding.activityFormularioProdutoImagem.load(url)
                })
                .setNegativeButton("nop", { _, _ -> })
                .show()
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