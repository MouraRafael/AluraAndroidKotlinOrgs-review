package br.com.alura.estudo.orgs.ui.activity

import android.icu.number.NumberFormatter
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.estudo.orgs.databinding.ActivityProdutosDetalhesBinding
import br.com.alura.estudo.orgs.model.Produto
import br.com.alura.estudo.orgs.tentaCarregarImagem
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import java.text.NumberFormat
import java.util.Locale

class DetalhesProdutoActivity:AppCompatActivity() {
    private val binding by lazy {
        ActivityProdutosDetalhesBinding.inflate(layoutInflater)
    }
    val imageLoader by lazy {
        ImageLoader.Builder(this)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        supportActionBar?.hide()
        val produto:Produto = intent.getParcelableExtra("chave")!!

        val precoFormatado = NumberFormat.getCurrencyInstance(Locale("pt", "br")).format(produto.preco)

        binding.activityDetalhesProdutoValor.text = precoFormatado
        binding.activityDetalhesProdutoTitulo.text = produto.nome
        binding.activityDetalhesProdutoDetalhes.text = produto.descricao
        binding.activityDetalhesProdutoImagem.tentaCarregarImagem(produto.imagem,imageLoader)


    }
}