package br.com.alura.estudo.orgs

import android.widget.ImageView
import coil.ImageLoader
import coil.load

fun ImageView.tentaCarregarImagem(url:String? = null,imageLoader: ImageLoader){
    load(url,imageLoader) {
        placeholder(R.drawable.placeholder)
        fallback(R.drawable.imagem_padrao)
        error(R.drawable.erro)

    }
}