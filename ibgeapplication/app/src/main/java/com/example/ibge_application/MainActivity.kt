package com.example.ibge_application

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var preferencias: SharedPreferences? = null
    lateinit var region: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferencias = getPreferences(Context.MODE_PRIVATE)
        val conteudo = preferencias?.getInt("id", 0);

        if (conteudo != 0 && conteudo != null) {
            val intent = Intent(baseContext, ConteudoActivity::class.java)
            startActivity(intent)
        }

        region = findViewById(R.id.et_regiao)
    }

    fun pesquisar(view: View) {
        val id = this.getRegionId(region.text.toString())
        if (id == -1) {
            Toast.makeText(this, R.string.erro_01, Toast.LENGTH_SHORT).show()
        } else {
            val editor = preferencias?.edit()
            editor?.putInt(R.string.id.toString(), id)
            editor?.commit()

            val intent = Intent(baseContext, ConteudoActivity::class.java)
            startActivity(intent)
        }
    }

    fun getRegionId(string: String): Int {
        var id: Int = -1;
        val array = arrayOf(getString(R.string.norte), getString(R.string.nordeste),
                getString(R.string.sudeste), getString(R.string.sul), getString(R.string.centro_oeste))

        for (i in array.indices) {
            if (array[i] == string.toLowerCase()) {
                id = i + 1;
            }
        }
        return id
    }

}