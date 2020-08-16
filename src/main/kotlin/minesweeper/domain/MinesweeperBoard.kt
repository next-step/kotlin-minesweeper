package minesweeper.domain

class MinesweeperBoard(val boardSize: BoardSize, val mineNumber: MineNumber) {
    private var _minBoard = MutableList(boardSize.height.length * boardSize.width.length) { BoardType.LOAD }
    private var _minesweeperBoard: MutableList<List<BoardType>> = MutableList(boardSize.height.length) { List(boardSize.width.length) { BoardType.LOAD } }
    val minesweeperBoard get() = _minesweeperBoard.toList()

    init {
        for (i in 0 until mineNumber.mineNumber) {
            _minBoard[i] = BoardType.MINE
        }
        _minBoard = _minBoard.shuffled().toMutableList()
        for (i in 0 until boardSize.height.length) {
            val startNumber = i * boardSize.width.length
            _minesweeperBoard[i] = _minBoard.subList(startNumber, startNumber + boardSize.width.length)
        }
    }
}
