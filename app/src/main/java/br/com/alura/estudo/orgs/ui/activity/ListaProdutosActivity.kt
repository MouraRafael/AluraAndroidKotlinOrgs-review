package br.com.alura.estudo.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.estudo.orgs.database.AppDataBase
import br.com.alura.estudo.orgs.database.dao.ProdutoDao
import br.com.alura.estudo.orgs.databinding.ActivityListaProdutosBinding
import br.com.alura.estudo.orgs.ui.adapter.ListaProdutosAdapter

class ListaProdutosActivity : AppCompatActivity() {

    private lateinit var dao: ProdutoDao
    val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }
    private val adapter = ListaProdutosAdapter(
        context = this
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Orgs Produtos"
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()



    }



    override fun onResume() {
        super.onResume()
        val roomDb = AppDataBase.instanciar(this);
        dao = roomDb.dao()
        adapter.atualiza(dao.pegaTodos())

        val swipe = binding.activityListaProdutosSwipe;
        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
            adapter.atualiza(dao.pegaTodos())
        }

    }

    private fun configuraFab() {
        val fab = binding.activityListaProdutosFab
        fab.setOnClickListener {
            Log.i("FAB","CLicado")
            vaiParaFormularioProduto()

        }
    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter

        adapter.quandoClicaNoItem = {
            val intent = Intent(
                this,
                DetalhesProdutoActivity::class.java
            ).apply {
                putExtra("chave", it)
            }
            startActivity(intent)
        }
        adapter.quandoClicaEmEditar = {
            Intent(this,FormularioProdutoActivity::class.java).apply {
                putExtra("chaveProduto",it)
                startActivity(this)
            }
        }
        adapter.quandoClicaEmRemover = {
            dao.remove(it)
            adapter.atualiza(dao.pegaTodos())
        }
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
*
* PASSO 4: Criar uma classe Interna do tipo ViewHolder extendendo de RecyclerView.ViewHolder para pasa substituir a referencia genérica que vem por padrão
* O ViewHolder recebe uma  View que será que será passado ao construtor da classe mae;
*
* Passo 5: Criar a View dentro do OnCreateViewHoldedr
* usar o LayoutInflater().from() para gerar um layout inflater, exigindo um contexto.
* Recomenda-se passar esse c ontexto através do do construtor do Adapter
* A partir do layoutInflater criado , usa-se o metodo inflate() passando como argumentos o resource(o layout que queremos criar)
* root: que e o viewgroup para onde enviaremos nosso RecyclerView(esse argumento é passado como argumento
* do onCreateViewHolder), o attach to root, que determinará se anexaremos essa  view diretamente no root
* ou se isso será feito em outro momento, por padrão se deixa isso como falso no RecyclerView
* O metodo inflate retornara a view que sera passada ao viewlholder
*
*
* Passo 6: Configuração do binding, pegando cada uma dasa views e colocando o valor esperado
* No onBindViewHolder, pega-se o item da lista passada com lista[posicao] e o passe como argumento para o método
* do viewHolder que fara o vinculo, se o metodo nao existir, crie-o
*
* Passo 7: vinculo de informacoes
*   pegar ou criar o metodo de vinculo no viewholder
*   A referencia ded view da classe viewholder tem e referencia dos elementos através do itemView
* com o itemView.findViewById
* e entao e so passar os dados do produto recebido para a view recebida
*
*  Passo 8: Na activity,passe o contexto e lista para o recyclerview.adapter
*
* Passo 9: Determinar o Layout Manager, isso pode ser feito em codigo usando o recyclerview.LayoutManager
* ou no xml da Recycler view passando através do app:layoutManager
* */