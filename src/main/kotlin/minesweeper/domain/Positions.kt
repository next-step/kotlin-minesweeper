package minesweeper.domain

class Positions(val positions: List<Position>) : List<Position> by positions {

    fun createRandomMinePosition(mineCount: Int): List<Position> {
        return positions.shuffled().take(mineCount)
    }
}
