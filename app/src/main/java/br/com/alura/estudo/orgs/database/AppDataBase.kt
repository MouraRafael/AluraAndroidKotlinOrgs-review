package br.com.alura.estudo.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        fun instanciar(context: Context):AppDataBase{
            return Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                "orgs.db"
            )
                .allowMainThreadQueries()
                .build()
        }
    }
}