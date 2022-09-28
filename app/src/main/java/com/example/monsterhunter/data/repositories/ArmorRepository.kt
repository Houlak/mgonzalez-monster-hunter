package com.example.monsterhunter.data.repositories

import com.example.monsterhunter.data.Api
import com.example.monsterhunter.data.responses.GetArmorsFailure
import com.example.monsterhunter.data.responses.GetArmorsResponse
import com.example.monsterhunter.data.responses.GetArmorsSuccess

class ArmorRepository(private val api: Api) : IArmorRepository {

    override suspend fun fetchArmors(): GetArmorsResponse {
        val response = api.fetchArmor()
        if (response.isSuccessful) {
            return GetArmorsSuccess(response.body() ?: emptyList())
        }
        return GetArmorsFailure(Throwable(response.raw().message))

    }
}