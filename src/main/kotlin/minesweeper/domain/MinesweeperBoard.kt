package minesweeper.domain

class MinesweeperBoard(val boardSize: BoardSize, val mineNumber: MineNumber) {
    private var _minesweeperBoard: MutableList<List<Cell>> =
        MutableList(boardSize.height.length) { List(boardSize.width.length) { Cell(CellType.LOAD) } }
    val minesweeperBoard get() = _minesweeperBoard.toList()

    init {
        var withoutMineBoard = MutableList(boardSize.height.length * boardSize.width.length) { Cell(CellType.LOAD) }
        for (i in 0 until mineNumber.mineNumber) {
            withoutMineBoard[i] = Cell(CellType.MINE)
        }
        withoutMineBoard = withoutMineBoard.shuffled().toMutableList()
        for (i in 0 until boardSize.height.length) {
            val startNumber = i * boardSize.width.length
            _minesweeperBoard[i] = withoutMineBoard.subList(startNumber, startNumber + boardSize.width.length)
        }
        calculateMine()
    }

    private fun calculateMine() {
        minesweeperBoard.asSequence()
            .forEachIndexed { row, list ->
                list.forEachIndexed { column, cell ->
                    if (cell.cellType == CellType.MINE) checkNeighboringMine(row, column)
                }
            }
    }

    private fun checkNeighboringMine(row: Int, column: Int) {
        val up = maxOf(row - 1, 0)
        val down = minOf(row + 1, boardSize.height.length - 1)
        val left = maxOf(column - 1, 0)
        val right = minOf(column + 1, boardSize.width.length - 1)

        for (i in up..down) {
            minesweeperBoard[i].subList(left, right + 1).forEach { it.addMine() }
        }
    }
}
