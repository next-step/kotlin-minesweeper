package minesweeper.domain

object BoardGenerator {
    fun create(height: Int, width: Int, mineCount: Int): MineBoard {
        val board = createBoard(height, width)

        repeat(mineCount) {
            placeMines(board)
        }
        return board
    }
    private fun createBoard(height: Int, width: Int): MineBoard {
        val boardInfo = (1..height).map { BoardRow((1..width).map { Cell(CellType.EMPTY) }) }
        return MineBoard(boardInfo)
    }
    private fun placeMines(board: MineBoard) {
        val height = board.boardInfo.size
        val width = board.boardInfo[0].rowInfo.size
        var placed = false

        while (!placed) {
            val randomHeight = (0 until height).random()
            val randomWidth = (0 until width).random()
            val cell = board.boardInfo[randomHeight].rowInfo[randomWidth]

            if (cell.type == CellType.EMPTY) {
                cell.type = CellType.MINE
                placed = true
            }
        }
    }
}
