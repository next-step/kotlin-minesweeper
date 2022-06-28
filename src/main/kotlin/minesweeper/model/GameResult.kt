package minesweeper.model

class GameResult private constructor(
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

        fun from(board: MineBoard): GameResult {
            val boardResult = board.board.map { generateBoardRow(it, board) }
            val boardRows = boardResult.flatten()

            if (boardRows.contains(MINE_MARK)) {
                return GameResult(boardResult, GameStatus.LOST)
            }

            val closeMarkCount = boardRows.count { it == CLOSE_MARK }
            if (board.mineCount == closeMarkCount) {
                return GameResult(boardResult, GameStatus.WIN)
            }

            return GameResult(boardResult, GameStatus.ONGOING)
        }

        private fun generateBoardRow(cells: Cells, board: MineBoard): List<String> {
            val boardRow = mutableListOf<String>()
            for (cell in cells.cells) {
                if (cell.isMineAndOpened()) {
                    boardRow.add(MINE_MARK)
                    continue
                }

                if (cell.isOpened) {
                    boardRow.add(cell.findSurroundingMineCountSum(board).toString())
                    continue
                }

                boardRow.add(CLOSE_MARK)
            }

            return boardRow
        }
    }
}
