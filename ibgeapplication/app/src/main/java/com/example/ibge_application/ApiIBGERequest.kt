package com.example.ibge_application

import retrofit2.Call
import retrofit2.http.GET

interface ApiIBGERequest {
    @GET("/localidades/estados/{uf}/municipios")
    fun getMunicipios() : Call<List<Municipio>>
}