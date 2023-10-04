package br.com.alura.estudo.orgs.database.convertes

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converter {

    @TypeConverter
    fun doubleToBigDecimal(valor:Double?):BigDecimal{
        return valor?.let { BigDecimal(valor.toString()) }?:BigDecimal.ZERO
    }
    @TypeConverter
    fun bigDecimalToDouble(valor:BigDecimal?):Double?{
        return valor?.let { valor.toDouble() }
    }
}