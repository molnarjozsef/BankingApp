package model.api

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
)
