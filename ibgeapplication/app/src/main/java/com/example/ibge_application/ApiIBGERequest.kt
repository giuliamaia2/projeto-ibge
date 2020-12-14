package com.example.ibge_application

import retrofit2.Call
import retrofit2.http.GET

interface ApiIBGERequest {
    @GET("/localidades/regioes/{id}/estados")
    fun getEstados() : Call<List<Estado>>
}