package com.example.monsterhunter.data

import com.example.monsterhunter.data.models.Armor
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("armor")
    suspend fun fetchArmor(): Response<List<Armor>>
}