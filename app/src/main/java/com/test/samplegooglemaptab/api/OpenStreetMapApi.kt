package com.test.samplegooglemaptab.api

import retrofit2.http.GET

data class College(
    val lat: String,
    val lon: String,
    val display_name: String
)

interface OpenStreetMapApi {
    @GET("search?q=college+near+Singapore&format=json")
    suspend fun getNearbyColleges(): List<College>
}