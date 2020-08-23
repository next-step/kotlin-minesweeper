package minesweeper.domain

interface Spot {
    val streetNumber: Int
    val nthPosition: Int

    fun addCountIfIsMineSpot(spot: Spot)

    fun getMineCount(): Int
}

enum class SpotSymbol(val symbol: String) {
    MINE("✴")
}
