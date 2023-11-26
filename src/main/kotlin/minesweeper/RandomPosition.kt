package minesweeper

class RandomPosition(
    gameBoard: GameBoard
) : PositionGenerateStrategy {

    private val rowRange = IntRange(0, gameBoard.width - 1)
    private val colRange = IntRange(0, gameBoard.height - 1)

    override fun generate(): Position =
        Position(rowRange.random(), colRange.random())
}
