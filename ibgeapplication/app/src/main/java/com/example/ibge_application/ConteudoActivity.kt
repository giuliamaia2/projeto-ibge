package com.example.ibge_application

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConteudoActivity : AppCompatActivity() {

    var preferencias: SharedPreferences? = null
    lateinit var llConteudo: LinearLayout

    override fun onCreate(savedInstanceState: Bundle??) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        preferencias = getPreferences(Context.MODE_PRIVATE)
        var id = preferencias?.getInt("id", 0);
        if (id == null || id == 0) {
            id = -1
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }

        llConteudo = findViewById(R.id.ll_conteudo)
        consumirApi(id)
    }


    fun consumirApi(id: Int) {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://servicodados.ibge.gov.br/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val requests = retrofit.create(ApiIBGERequest::class.java)

        val callEstados = requests.getEstados(id)

        callEstados.enqueue(object : Callback<List<Estado>> {
            override fun onFailure(call: Call<List<Estado>>, t: Throwable) {
                Toast.makeText(applicationContext, R.string.erro_01, Toast.LENGTH_SHORT).show()
                println(R.string.erro_01)
            }

            override fun onResponse(call: Call<List<Estado>>, response: Response<List<Estado>>) {
                response.body()?.forEach {
                    val novoEstado = TextView(baseContext)
                    novoEstado.text = getString(R.string.detalhes_estado, it.nome, it.sigla, it.regiao)
                    novoEstado.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                    novoEstado.setTextColor(Color.parseColor("#000000"))
                    llConteudo.addView(novoEstado)
                }
            }
        })
    }


}