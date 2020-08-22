package minesweeper.domain

interface Spot {
    val streetNumber: Int
    val nthPosition: Int

    fun addMineCount(spot: Spot)

    fun getMineCount(): Int
}

enum class SpotSymbol(val symbol: String) {
    MINE("✴")
}
