package minesweeper.domain

class Positions(val positions: List<Position>) {

    fun createRandomMinePosition(mineCount: Int): List<Position> {
        return positions.shuffled().take(mineCount)
    }

    companion object {
        fun of(gameBoardSize: GameBoardSize): Positions {
            val positions = ArrayList<Position>()
            repeat(gameBoardSize.height) { height ->
                repeat(gameBoardSize.width) { width ->
                    positions.add(Position(height, width))
                }
            }
            return Positions(positions)
        }
    }
}
