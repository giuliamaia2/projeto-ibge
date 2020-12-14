package com.example.ibge_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun pesquisar(view: View) {
        val region = view.findViewById<EditText>(R.id.et_regiao).text.toString();
        val id = this.getRegionId(region)
        if (id == -1) {
            Toast.makeText(this, "Região não existente", Toast.LENGTH_SHORT).show()
        } else {
           
        }
    }

    fun getRegionId(string: String): Int {
        val regions = listOf("norte", "nordeste", "sudeste", "sul", "centro-oeste")
        for (region in regions) {
            if (string.toLowerCase() == region) {
                return region.length + 1
            }
        }
        return -1
    }
}