package minesweeper.domain

class Positions(val positions: List<Position>) {

    fun createRandomMinePosition(mineCount: Int): List<Position> {
        return positions.shuffled().take(mineCount)
    }
}
