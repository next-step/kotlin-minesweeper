package domain

abstract class MineSweeperBoardGenerator(val boardSize: BoardSize, private val mines: Int) {
    open fun generate(): MineSweeperBoard {
        require(mines > 0) { "지뢰는 0보다 많아야 합니다." }
        require(mines < boardSize.width * boardSize.height) { "지뢰는 게임판의 칸 수보다 적어야 합니다." }

        val mineSweeperBoard = MineSweeperBoard(boardSize)
        setMines(mineSweeperBoard)
        return mineSweeperBoard
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

class RandomMineSweeperBoardGenerator(boardSize: BoardSize, mines: Int) :
    MineSweeperBoardGenerator(boardSize, mines)

class TestMineSweeperBoardGenerator : MineSweeperBoardGenerator(BoardSize(10, 10), 10) {
    override fun generate(): MineSweeperBoard {
        val mineSweeperBoard = MineSweeperBoard(boardSize)
        mineSweeperBoard.putMines(
            Position(3, 0),
            Position(7, 0),

            Position(2, 1),
            Position(4, 1),

            Position(0, 4),

            Position(6, 5),

            Position(2, 6),
            Position(6, 6),

            Position(6, 7),
            Position(9, 7),
        )
        return mineSweeperBoard
    }
}
