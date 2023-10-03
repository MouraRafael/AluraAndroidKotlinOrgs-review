package br.com.alura.estudo.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import br.com.alura.estudo.orgs.R

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            val botalSalvar: Button = findViewById(R.id.botao_salvar)
            Log.i("FormProduto", "onCreate $nome")

        } //Listener antigo

    }
}