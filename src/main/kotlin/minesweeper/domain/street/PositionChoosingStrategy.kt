package minesweeper.domain.street

interface PositionChoosingStrategy {
    fun getPosition(streetWidth: Int): Int
}
