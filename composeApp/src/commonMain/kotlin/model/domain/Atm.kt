package model.domain

data class Atm(
    val lat: Double,
    val lon: Double,
    val name: String?,
    val postcode: String?,
    val city: String?,
    val street: String?,
    val houseNumber: String?,
    val wheelchair: Boolean?,
    val indoor: Boolean?,
    val cashIn: Boolean?,
)
