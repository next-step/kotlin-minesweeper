package domain

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
