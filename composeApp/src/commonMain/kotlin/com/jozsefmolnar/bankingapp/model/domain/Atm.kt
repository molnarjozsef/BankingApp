package com.jozsefmolnar.bankingapp.model.domain

import com.jozsefmolnar.bankingapp.components.GpsPosition

data class Atm(
    val lat: Double,
    val lon: Double,
    val name: String? = null,
    val postcode: String? = null,
    val city: String? = null,
    val street: String? = null,
    val houseNumber: String? = null,
    val wheelchair: Boolean? = null,
    val indoor: Boolean? = null,
    val cashIn: Boolean? = null,
) {
    fun getLocation() = GpsPosition(latitude = lat, longitude = lon)
}
