package minesweeper.model

class MineBoardResult private constructor(
    val boardRows: List<List<String>>
) {

    companion object {
        private const val MINE_MARK = "*"

        fun from(board: MineBoard): MineBoardResult {
            val boardResult = board.board.map { generateBoardRow(it, board) }
            return MineBoardResult(boardResult)
        }

        private fun generateBoardRow(cells: Cells, board: MineBoard): List<String> {
            val boardRow = mutableListOf<String>()
            for (cell in cells.cells) {
                if (cell.isMine()) {
                    boardRow.add(MINE_MARK)
                    continue
                }
                boardRow.add(cell.findSurroundingMineCountSum(board).toString())
            }

            return boardRow
        }
    }
}