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
        _minesweeperBoard.forEachIndexed { row, list -> calculateColumnMine(list, row) }
    }

    private fun calculateColumnMine(list: List<Cell>, row: Int) {
        list
            .filter { it.cellType == CellType.MINE }
            .forEachIndexed { column, _ -> checkNeighboringMine(row, column) }
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

    fun openCell(position: Position): PlayState {
        val selectedCell = getCell(position)
        if (selectedCell.cellType == CellType.MINE) return PlayState.LOSE
        if (selectedCell.isOpen) return PlayState.PLAYING

        selectedCell.open()

        if (selectedCell.numberOfNeighboringMine == 0) openNeighboringArea(position)

        if (getCountOfOpenCells() == boardSize.count - mineNumber.mineNumber) return PlayState.WIN
        return PlayState.PLAYING
    }

    fun openAll() {
        minesweeperBoard.forEach { list -> list.forEach { it.isOpen = true } }
    }

    private fun openNeighboringArea(position: Position) {
        val x: XPosition = position.x
        val y: YPosition = position.y
        val up = maxOf(y.value - 1, 0)
        val down = minOf(y.value + 1, boardSize.height.length - 1)
        val left = maxOf(x.value - 1, 0)
        val right = minOf(x.value + 1, boardSize.width.length - 1)
        for (_row in up..down) {
            minesweeperBoard[_row].subList(left, right + 1)
                .forEachIndexed { _column,
                    cell ->
                    if (!cell.isOpen) openCell(Position.of(left + _column + 1, _row + 1))
                }
        }
    }

    private fun getCell(position: Position): Cell {
        val column: Int = position.x.value
        val row: Int = position.y.value
        return minesweeperBoard[row][column]
    }

    private fun getCountOfOpenCells(): Int = minesweeperBoard.sumBy { list -> list.count { it.isOpen } }
}
