package br.com.alura.estudo.orgs

import android.app.Activity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.TextView

class MainActivity:Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.nome).setText("Abacate")
        findViewById<TextView>(R.id.nome).setTextSize(TypedValue.COMPLEX_UNIT_SP,25.5f)

    }
}