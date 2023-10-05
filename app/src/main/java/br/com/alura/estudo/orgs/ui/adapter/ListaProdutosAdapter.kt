package br.com.alura.estudo.orgs.ui.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.estudo.orgs.R
import br.com.alura.estudo.orgs.databinding.ProdutoItemBinding
import br.com.alura.estudo.orgs.model.Produto
import br.com.alura.estudo.orgs.tentaCarregarImagem
import br.com.alura.estudo.orgs.ui.activity.DetalhesProdutoActivity
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import java.text.NumberFormat
import java.util.Locale

class ListaProdutosAdapter(private val context: Context, produtos: List<Produto> = emptyList()) :
    RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {
    private val produtos = produtos.toMutableList();

    class ViewHolder(private val context: Context, private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageLoader = ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()



        fun mostraPopUp(v:View){
            val popupMenu = PopupMenu(context,v)
            val inflater:MenuInflater = popupMenu.menuInflater
            inflater.inflate(R.menu.menu_editar,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {

                when(it.itemId){
                    R.id.menu_detalhes_produto_editar->{
                        Log.i("DetalhesProduto","onOptionsItemSelected Editar")
                        true
                    }
                    R.id.menu_detalhes_produto_excluir->{
                        Log.i("DetalhesProduto","onOptionsItemSelected Excluir")
                        true
                    }
                    else ->false
                }
            }
            popupMenu.show()
        }

        fun vincula(produto: Produto) {
            itemView.setOnClickListener{
                val intent = Intent(context, DetalhesProdutoActivity::class.java)
                intent.putExtra("chave",produto)
                context.startActivity(intent)

            }

            itemView.setOnLongClickListener{
                mostraPopUp(itemView)

                true
            }



            val nome = binding.produtoItemNome
            nome.text = produto.nome;

            binding.produtoItemDescricao.text = produto.descricao
            val formatador: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            val valorFormatadoEmMoeda: String = formatador.format(produto.preco)
            binding.produtoItemValor.text = valorFormatadoEmMoeda
            //binding.imageView.load("https://www.cleverfiles.com/howto/wp-content/uploads/2018/03/minion.jpg")
            //binding.imageView.load(R.drawable.imagem_padrao)

            val visibilidade = if (produto.imagem != null){
                View.VISIBLE
            }else{
                View.GONE
            }


            binding.imageView.visibility = visibilidade

            binding.imageView.tentaCarregarImagem(produto.imagem, imageLoader)

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(context, binding)

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
        Log.i("Chegou", "daset mudou")
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