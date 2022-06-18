package minesweeper.domain

class Positions(val positions: List<Position>) : List<Position> by positions {

    fun createRandomMinePosition(mineCount: Int): Positions {
        return Positions(positions.shuffled().take(mineCount))
    }
}
