package br.com.alura.estudo.orgs.database.dao

import androidx.room.Insert
import androidx.room.Query
import br.com.alura.estudo.orgs.model.Produto

interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun pegaTodos():List<Produto>

    @Insert
    fun salva(vararg produto: Produto)
}