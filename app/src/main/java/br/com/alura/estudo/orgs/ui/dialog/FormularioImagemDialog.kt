package br.com.alura.estudo.orgs.ui.dialog

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.alura.estudo.orgs.databinding.FormularioImagemBinding
import br.com.alura.estudo.orgs.tentaCarregarImagem
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

class FormularioImagemDialog( val context: Context) {

    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    fun mostra(url:String?=null,quandoImagemCarregada:(textoUrl:String)->Unit){


        FormularioImagemBinding.inflate(LayoutInflater.from(context)).apply {
            //carrega imagem se ja houver sido carregada antes
            url?.let{
                formularioImagemImageview.tentaCarregarImagem(url,imageLoader)
                formularioImagemUrl.setText(it)
            }
            formularioImagemBotaoCarregar.setOnClickListener {
                val url = formularioImagemUrl.text.toString()
                formularioImagemImageview.tentaCarregarImagem(url,imageLoader)
            }

            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("ok", { _, _ ->
                    val  url = formularioImagemUrl.text.toString()
                    Log.i("URL",url)
                    quandoImagemCarregada(url)
                })
                .setNegativeButton("nop", { _, _ -> })
                .show()
        }




    }
}