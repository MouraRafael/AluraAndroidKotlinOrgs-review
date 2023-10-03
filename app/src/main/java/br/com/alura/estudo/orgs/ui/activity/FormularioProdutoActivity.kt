package br.com.alura.estudo.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import br.com.alura.estudo.orgs.R
import br.com.alura.estudo.orgs.dao.ProdutosDao
import br.com.alura.estudo.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = ProdutosDao();
        val campoNome = findViewById<EditText>(R.id.form_nome)
        val nome = campoNome.text.toString();
        val botalSalvar:Button = findViewById(R.id.botao_salvar)
        Log.i("FormProduto","onCreate $nome")

//        botalSalvar.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(p0: View?) {
//                val campoNome = findViewById<EditText>(R.id.form_nome)
//                val nome = campoNome.text.toString();
//                val botalSalvar:Button = findViewById(R.id.botao_salvar)
//                Log.i("FormProduto","onCreate $nome")
//            }
//        }) //Listener antigo
        botalSalvar.setOnClickListener {
            val campoNome = findViewById<EditText>(R.id.form_nome)
            val nome = campoNome.text.toString();

            val campoDescricao = findViewById<EditText>(R.id.form_descricao)
            val descricao = campoDescricao.text.toString()
            val campoValor = findViewById<EditText>(R.id.form_valor)
            val valorEmTexto = campoValor.text.toString()

            val valor = if(valorEmTexto.isNullOrBlank()){
                BigDecimal.ZERO
            }else{
                BigDecimal(valorEmTexto)
            }

            val produto = Produto(nome, descricao, valor)

            dao.adiciona(produto)


            Log.i("FormProduto", "onCreate $produto")
            Log.i("FormProduto", "onCreate ${dao.buscaTodos()}")

        } //Listener antigo

    }
}