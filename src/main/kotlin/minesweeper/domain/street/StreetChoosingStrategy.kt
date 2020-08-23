package minesweeper.domain.street

interface StreetChoosingStrategy {
    fun getStreetNumber(streetCount: Int): Int
}
