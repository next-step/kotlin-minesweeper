package minesweeper.model

class MineBoardResult private constructor(
    val boardRows: List<List<String>>,
    val gameStatus: GameStatus
) {

    companion object {
        private const val MINE_MARK = "*"
        private const val CLOSE_MARK = "C"

        fun from(board: MineBoard): MineBoardResult {
            val boardResult = board.board.map { generateBoardRow(it, board) }
            val boardRows = boardResult.flatten()

            if (boardRows.contains(MINE_MARK)) {
                return MineBoardResult(boardResult, GameStatus.LOST)
            }

            val closeMarkCount = boardRows.count { it == CLOSE_MARK }
            if (board.mineCount == closeMarkCount) {
                return MineBoardResult(boardResult, GameStatus.WIN)
            }

            return MineBoardResult(boardResult, GameStatus.ONGOING)
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
