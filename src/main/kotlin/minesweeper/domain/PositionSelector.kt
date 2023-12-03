package minesweeper.domain

interface PositionSelector {
    fun select(positions: Set<Position>, selectNum: Int): Set<Position>
}
