package br.com.alura.estudo.orgs.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import br.com.alura.estudo.orgs.database.convertes.Converter
import br.com.alura.estudo.orgs.database.dao.ProdutoDao
import br.com.alura.estudo.orgs.model.Produto

@Database(entities = [Produto::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDataBase:RoomDatabase() {

    abstract fun dao():ProdutoDao
}