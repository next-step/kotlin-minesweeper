package minesweeper.domain.game

import minesweeper.domain.board.MineBoard
import minesweeper.domain.position.Position

data class MinesweeperGame(
    val board: MineBoard,
    val positionToOpen: () -> Position,
) {
    var result: GameResult? = null
        private set

    fun run() {
        check(isEnd().not())
        val position = positionToOpen()
        if (board.isMine(position)) {
            result = GameResult.LOSS
            return
        }
        doOpen(position)
        if (board.isAllOpened()) {
            result = GameResult.WIN
            return
        }
    }

    fun isEnd() = result != null

    private fun doOpen(position: Position) {
        if (board.isOpened(position)) return

        val cell = board.open(position)

        if (cell.isZeroMineCount()) {
            openAdjacentPositions(cell.position)
        }
    }

    private fun openAdjacentPositions(position: Position) {
        val adjacentPositions = position.adjacentPositions.filter { board.isValidPosition(it) }
        adjacentPositions.forEach { doOpen(it) }
    }
}
