package com.example.monsterhunter.data.repositories

import com.example.monsterhunter.data.responses.GetArmorsResponse

interface IArmorRepository {
    suspend fun fetchArmors(): GetArmorsResponse
}