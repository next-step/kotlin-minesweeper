package minesweeper.domain.mine

import minesweeper.domain.board.BoardRange
import minesweeper.domain.position.MineSweeperPosition
import minesweeper.domain.position.MineSweeperPositions
import minesweeper.domain.position.Position
import minesweeper.domain.position.PositionType

object MinePositionGenerator {

    fun createMinePositions(mineQuantity: Int, boardRange: BoardRange): MineSweeperPositions {
        val randomPositions = createRandomPositions(mineQuantity, boardRange)
        return MineSweeperPositions(
            randomPositions.map {
                MineSweeperPosition(position = it, type = PositionType.MINE)
            }
        )
    }

    private fun createRandomPositions(
        mineQuantity: Int,
        boardRange: BoardRange
    ): MutableSet<Position> {
        val mines = mutableSetOf<Position>()
        while (mines.size < mineQuantity) {
            val randomXPosition = boardRange.getRandomWidth()
            val randomYPosition = boardRange.getRandomHeight()
            val position = Position(x = randomXPosition, y = randomYPosition)
            mines.add(position)
        }
        return mines
    }
}
