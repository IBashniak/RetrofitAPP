package dto

import com.google.gson.annotations.SerializedName


data class SuperHero(
    @field:SerializedName("name")
    val superHeroName: String
)