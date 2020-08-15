package minesweeper.domain

class MinesweeperBoard(boardSize: BoardSize, mineNumber: MineNumber) {
    private var _minesweeperBoard: List<List<BoardType>> = List(boardSize.height.length) { List(boardSize.width.length) { BoardType.LOAD } }
    val minesweeperBoard get() = _minesweeperBoard.toList()
    init {

        _minesweeperBoard = _minesweeperBoard.shuffled()
    }
}
