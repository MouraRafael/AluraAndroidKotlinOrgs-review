package br.com.alura.estudo.orgs.ui.activity

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.estudo.orgs.R
import br.com.alura.estudo.orgs.ui.adapter.ListaProdutosAdapter

class MainActivity:Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val nome: TextView = findViewById<TextView>(R.id.nome)
//        nome.text = "Cesta de Futas"
//
//        val descricao = findViewById<TextView>(R.id.descricao)
//        descricao.text = "Laranja, maçã e uva"
//
//        val valor:TextView = findViewById(R.id.valor)
//        valor.text = "19,99"

        val recyclerView:RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = ListaProdutosAdapter()

        //findViewById<TextView>(R.id.nome).setTextSize(TypedValue.COMPLEX_UNIT_SP,25.5f)

    }
}

/*
* RecyclerView
* passo 1:
* Criar item que será modelo da Recycler view
*
* Passo 2: Adicionar A RecyclerView no viewGroup  Desejado
*
* Passo 3:  Configurar o Componente responsável por fazer a lógica do RecyclerVIew Acontece
* */