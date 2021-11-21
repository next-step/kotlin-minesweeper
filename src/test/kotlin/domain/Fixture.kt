package domain

import dto.BoardDto

class Fixture {
    private val board = Board(height, width)
    operator fun component1(): Board = board
    operator fun component3(): MineNumber = MineNumber(minePositions.size)
    operator fun component2(): PositionSelector = object : PositionSelector(height, width) {
        override fun selectMinePositions(mineNumber: MineNumber, excludedPosition: Position) = minePositions
    }

    fun drawBoard(): String = BoardDto(board).draw()
    fun boardMineNumbers(): String = BoardDto(board).mineNumbers()
    fun renderBoard(): String = BoardDto(board).render()

    companion object {
        val minePositions = listOf(
            Position(1, 4),
            Position(1, 8),
            Position(2, 3),
            Position(2, 5),
            Position(5, 1),
            Position(6, 7),
            Position(7, 3),
            Position(7, 7),
            Position(8, 7),
            Position(8, 10)
        )
        private const val height = 10
        private const val width = 10
    }
}
