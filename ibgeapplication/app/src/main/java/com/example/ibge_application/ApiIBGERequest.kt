package com.example.ibge_application

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiIBGERequest {
    @GET("/localidades/regioes/{id}/estados")
    fun getEstados(@Path("id") id : Int) : Call<List<Estado>>
}