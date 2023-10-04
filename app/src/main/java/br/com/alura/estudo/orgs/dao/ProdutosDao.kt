package br.com.alura.estudo.orgs.dao

import br.com.alura.estudo.orgs.model.Produto
import java.math.BigDecimal

class ProdutosDao {
    fun adiciona(produto:Produto){
        produtos.add(produto)
    }

    fun buscaTodos():List<Produto>{
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>(
            //Produto("Salada de Frutas","Laranja, Maçãs e uva,Laranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uvaLaranja, Maçãs e uva", BigDecimal("19.83"),"https://www.cleverfiles.com/howto/wp-content/uploads/2018/03/minion.jpg"),


        )
    }
}