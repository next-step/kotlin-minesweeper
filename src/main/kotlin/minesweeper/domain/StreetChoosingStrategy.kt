package minesweeper.domain

interface StreetChoosingStrategy {
    fun getStreetNumber(streetCount: Int): Int
}
