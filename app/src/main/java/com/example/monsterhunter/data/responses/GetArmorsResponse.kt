package com.example.monsterhunter.data.responses

import com.example.monsterhunter.data.models.Armor

sealed class GetArmorsResponse
class GetArmorsSuccess(val armors: List<Armor>) : GetArmorsResponse()
class GetArmorsFailure(val error: Throwable) : GetArmorsResponse()
