package br.com.alura.estudo.orgs.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.estudo.orgs.model.Produto

class ListaProdutosAdapter(private val produtos:List<Produto> ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
    override fun getItemCount(): Int {
        return produtos.size;
    }

}
