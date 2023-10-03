package br.com.alura.estudo.orgs.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class Produto(
   val nome:String,
   val descricao:String,
   val preco:BigDecimal,
   val imagem:String? = null
) : Parcelable
