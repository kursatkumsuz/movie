package com.kursatkumsuz.movie.data.model.detail

import com.google.gson.annotations.SerializedName

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso: String,
    val name: String
)