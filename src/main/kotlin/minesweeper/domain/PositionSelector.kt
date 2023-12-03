package minesweeper.domain

interface PositionSelector {
    fun select(positions: Positions, selectNum: Int): Positions
}
