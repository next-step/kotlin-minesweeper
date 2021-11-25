package domain

import dto.BoardDto

class Fixture {
    private val board = Board(HEIGHT, WIDTH)
    operator fun component1(): Board = board
    operator fun component3(): MineNumber = MineNumber(minePositions.size)
    operator fun component2(): PositionSelector = object : PositionSelector(HEIGHT, WIDTH) {
        override fun selectMinePositions(mineNumber: MineNumber, excludedPosition: Position) = minePositions
    }

    fun drawnBoard(): String = BoardDto(board).draw()
    fun mineNumbersOfBoard(): String = BoardDto(board).mineNumbers()
    fun renderedBoard(): String = BoardDto(board).render()

    companion object {
        private const val HEIGHT = 10
        private const val WIDTH = 10
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
    }
}
