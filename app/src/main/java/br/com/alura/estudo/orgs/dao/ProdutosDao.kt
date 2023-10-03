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
            Produto("Salada de Frutas","Laranja, Maçãs e uva", BigDecimal("19.83"))
        )
    }
}