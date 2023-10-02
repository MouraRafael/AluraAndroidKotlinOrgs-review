package br.com.alura.estudo.orgs

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class MainActivity:Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nome: TextView = findViewById<TextView>(R.id.nome)
        nome.text = "Cesta de Futas"

        val descricao = findViewById<TextView>(R.id.descricao)
        descricao.text = "Laranja, maçã e uva"

        val valor:TextView = findViewById(R.id.valor)
        valor.text = "19,99"


        //findViewById<TextView>(R.id.nome).setTextSize(TypedValue.COMPLEX_UNIT_SP,25.5f)

    }
}