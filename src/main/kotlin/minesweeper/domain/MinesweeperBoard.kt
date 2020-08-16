package minesweeper.domain

class MinesweeperBoard(val boardSize: BoardSize, val mineNumber: MineNumber) {
    private var _minesweeperBoard: MutableList<List<CellType>> = MutableList(boardSize.height.length) { List(boardSize.width.length) { CellType.LOAD } }
    val minesweeperBoard get() = _minesweeperBoard.toList()

    init {
        var withoutMineBoard = MutableList(boardSize.height.length * boardSize.width.length) { CellType.LOAD }
        for (i in 0 until mineNumber.mineNumber) {
            withoutMineBoard[i] = CellType.MINE
        }
        withoutMineBoard = withoutMineBoard.shuffled().toMutableList()
        for (i in 0 until boardSize.height.length) {
            val startNumber = i * boardSize.width.length
            _minesweeperBoard[i] = withoutMineBoard.subList(startNumber, startNumber + boardSize.width.length)
        }
    }
}
