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

    fun mostra(quandoImagemCarregada:(textoUrl:String)->Unit){
        val binding = FormularioImagemBinding.inflate(LayoutInflater.from(context));

        binding.formularioImagemBotaoCarregar.setOnClickListener {
            val url = binding.formularioImagemUrl.text.toString()
            binding.formularioImagemImageview.tentaCarregarImagem(url,imageLoader)
        }

        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("ok", { _, _ ->
                val  url = binding.formularioImagemUrl.text.toString()
                Log.i("URL",url)
                quandoImagemCarregada(url)
            })
            .setNegativeButton("nop", { _, _ -> })
            .show()
    }
}