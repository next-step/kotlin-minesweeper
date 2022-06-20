package minesweeper.domain

interface PositionsMaker {
    fun createRandomMinePosition(mineCount: Int): Positions
}
