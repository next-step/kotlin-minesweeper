package domain

abstract class MineSweeperBoardGenerator(val boardSize: BoardSize, private val mines: Int) {
    open fun generate(): MineSweeperBoard {
        require(mines > 0) { "지뢰는 0보다 많아야 합니다." }
        require(mines < boardSize.width * boardSize.height) { "지뢰는 게임판의 칸 수보다 적어야 합니다." }

        val mineSweeperBoard = MineSweeperBoard(boardSize)
        setMines(mineSweeperBoard)

        setCountAroundMines(mineSweeperBoard)
        return mineSweeperBoard
    }

    private fun setCountAroundMines(mineSweeperBoard: MineSweeperBoard) {
        mineSweeperBoard.allPositions().map { position ->
            if (!mineSweeperBoard.isMine(position)) {
                val count = mineSweeperBoard.getMineCountAround(position)
                mineSweeperBoard.getCell(position).countOfMinesAround = count
            }
        }
    }

    private fun setMines(mineSweeperBoard: MineSweeperBoard) {
        (0 until boardSize.width * boardSize.height)
            .shuffled()
            .take(mines)
            .map { Position(it % boardSize.width, it / boardSize.width) }
            .forEach {
                mineSweeperBoard.putMine(it)
            }
    }
}
