package domain

class MineSweeperBoard(val boardSize: BoardSize, private val mines: Int) {
    val board = Array(boardSize.width * boardSize.height) { Cell.createNormalCell() }

    init {
        require(mines > 0) { "지뢰는 0보다 많아야 합니다." }
        require(mines < boardSize.width * boardSize.height) { "지뢰는 게임판의 칸 수보다 적어야 합니다." }
        setMines()
    }

    private fun setMines() {
        (0 until boardSize.width * boardSize.height)
            .shuffled()
            .take(mines)
            .forEach {
                board[it] = Cell.createMineCell()
            }
    }
}
