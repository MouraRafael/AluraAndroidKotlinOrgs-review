package br.com.alura.estudo.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.alura.estudo.orgs.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun pegaTodos():List<Produto>

    @Insert
    fun salva(vararg produto: Produto)
}