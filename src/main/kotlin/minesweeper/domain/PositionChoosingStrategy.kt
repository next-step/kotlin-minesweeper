package minesweeper.domain

interface PositionChoosingStrategy {
    fun getPosition(streetWidth: Int): Int
}
