package com.example.monsterhunter.data.models

import android.os.Parcelable
import com.example.monsterhunter.R
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Armor(
    @Expose val id: Int,
    @Expose val type: String,
    @Expose val rank: String,
    @Expose val defense: Defense,
    @Expose val name: String,
    @Expose val slots: List<Slot>,
    @Expose val resistances: Resistances,
) : Parcelable {

    @Parcelize
    data class Defense(
        @Expose val base: Int,
        @Expose val max: Int,
        @Expose val augmented: Int,
    ) : Parcelable

    @Parcelize
    data class Slot(
        @Expose val rank: Int
    ) : Parcelable

    @Parcelize
    data class Resistances(
        @Expose val fire: Int,
        @Expose val water: Int,
        @Expose val ice: Int,
        @Expose val thunder: Int,
        @Expose val dragos: Int,
    ) : Parcelable

    enum class Types(val text: String, val iconRes: Int) {
        HEAD("head", R.drawable.ic_head),
        GLOVES("gloves", R.drawable.ic_gloves),
        CHEST("chest", R.drawable.ic_chest),
        LEGS("legs", R.drawable.ic_legs),
        WAIST("waist", R.drawable.ic_waist);

        companion object {
            fun getIconRes(text: String) = values().find { it.text == text }?.iconRes
        }
    }

}