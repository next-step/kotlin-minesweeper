package minesweeper.domain.mine

import minesweeper.domain.board.BoardRange
import minesweeper.domain.position.Positions
import minesweeper.domain.position.Position

object MinePositionGenerator {

    fun createMinePositions(mineQuantity: Int, boardRange: BoardRange): Positions {
        val mines = mutableSetOf<Position>()
        while (mines.size < mineQuantity) {
            val randomXPosition = boardRange.getRandomWidth()
            val randomYPosition = boardRange.getRandomHeight()
            val position = Position(x = randomXPosition, y = randomYPosition)
            mines.add(position)
        }
        return Positions(positions = mines.toList())
    }
}
