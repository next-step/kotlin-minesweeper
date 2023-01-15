package minesweeper.domain

import minesweeper.domain.GameState.PROGRESS
import minesweeper.domain.GameState.SKIP
import minesweeper.domain.GameState.TERMINATE

class Game(private val board: MineBoard) {

    private val rows = board.rowSize()
    private val columns = board.columnSize()

    fun open(targetPositionText: String): GameState {
        val index: Int = Position.toIndex(columns, targetPositionText)
        val target: Coordinate = board.coordinates[index]

        if (target.isMine()) return TERMINATE
        if (target.count > 0) {
            target.open()
            return SKIP
        }

        openAround(index)

        return PROGRESS
    }

    private fun openAround(index: Int) {
        val positionArray = Position.values()
        repeat(positionArray.size) {
            val position = positionArray[it]
            openPosition(position, index)
        }
    }

    private fun openPosition(position: Position, index: Int) {
        val nextIndex = position.calculate(rows, columns, index)
        if (nextIndex >= 0) {
            val state = openCoordinate(nextIndex)
            if (state == SKIP) return
            openAround(nextIndex)
        }
    }

    private fun openCoordinate(index: Int): GameState {
        val coordinate: Coordinate = board.coordinates[index]

        if (coordinate.isOpen()) return SKIP
        if (coordinate.isMine()) return SKIP
        if (coordinate.count > 0) {
            coordinate.open()
            return SKIP
        }

        coordinate.open()

        return PROGRESS
    }
}
