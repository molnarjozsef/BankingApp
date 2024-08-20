package com.jozsefmolnar.bankingapp.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AtmsResponse(
    val elements: List<Element>,
)

@Serializable
data class Element(
    val type: String,
    val id: Long,
    val lat: Double,
    val lon: Double,
    val timestamp: String,
    val version: Long,
    val changeset: Long,
    val user: String,
    val uid: Long,
    val tags: Tags,
)

@Serializable
data class Tags(
    val amenity: String,
    val brand: String?,
    val name: String?,
    val operator: String?,
    @SerialName("addr:city")
    val addrCity: String?,
    @SerialName("addr:housenumber")
    val addrHouseNumber: String?,
    @SerialName("addr:postcode")
    val addrPostcode: String?,
    @SerialName("addr:street")
    val addrStreet: String?,
    val wheelchair: String?,
    val indoor: String?,
    @SerialName("cash_in")
    val cashIn: String?,
)
