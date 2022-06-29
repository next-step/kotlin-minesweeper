package minesweeper.dto

import minesweeper.model.Cell
import minesweeper.model.Cells
import minesweeper.model.GameStatus
import minesweeper.model.MineBoard

class GameResultDto private constructor(
    val boardRows: List<List<String>>,
    val gameStatus: GameStatus
) {

    val isOngoing
        get() = gameStatus.isOngoing()

    val win
        get() = gameStatus.win()

    val lost
        get() = gameStatus.lost()

    companion object {
        private const val MINE_MARK = "*"
        private const val CLOSE_MARK = "C"

        fun from(board: MineBoard): GameResultDto {
            val boardResult = board.board.map { generateBoardRow(it, board) }
            val boardRows = boardResult.flatten()

            if (boardRows.contains(MINE_MARK)) {
                return GameResultDto(boardResult, GameStatus.LOST)
            }

            val closeMarkCount = boardRows.count { it == CLOSE_MARK }
            if (board.mineCount == closeMarkCount) {
                return GameResultDto(boardResult, GameStatus.WIN)
            }

            return GameResultDto(boardResult, GameStatus.ONGOING)
        }

        private fun generateBoardRow(cells: Cells, board: MineBoard): List<String> =
            cells.cells.map { findMarkOf(it, board) }

        private fun findMarkOf(cell: Cell, board: MineBoard): String {
            if (cell.isMineAndOpened()) {
                return MINE_MARK
            }

            if (cell.isOpened) {
                return cell.findSurroundingMineCountSum(board).toString()
            }

            return CLOSE_MARK
        }
    }
}
