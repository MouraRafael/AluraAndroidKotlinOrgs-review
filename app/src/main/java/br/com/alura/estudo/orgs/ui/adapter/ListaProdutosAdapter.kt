package br.com.alura.estudo.orgs.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.estudo.orgs.R
import br.com.alura.estudo.orgs.databinding.ProdutoItemBinding
import br.com.alura.estudo.orgs.model.Produto

class ListaProdutosAdapter(private val context: Context, produtos: List<Produto>) :
    RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {
    private val produtos = produtos.toMutableList();

    class ViewHolder(private val binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun vincula(produto: Produto) {
            val nome = binding.produtoItemNome
            nome.text = produto.nome;

            binding.produtoItemDescricao.text = produto.descricao
            binding.produtoItemValor.text = produto.preco.toPlainString()

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int {
        return produtos.size;
    }

    fun atualiza(buscaTodos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(buscaTodos)
        Log.i("Chegou","daset mudou")
        notifyDataSetChanged()
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
* O metodo inflate retornara a view que sera passada ao viewholder
*/
/*
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