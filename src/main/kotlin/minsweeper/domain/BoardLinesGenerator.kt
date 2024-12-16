package minsweeper.domain

class BoardLinesGenerator(
    private val minePositionsGenerator: MinePositionsGenerator = RandomMinePositionsGenerator(),
    private val aroundMineCountJudge: AroundMineCountJudge,
) {

    fun generate(boardSize: BoardSize, mineCount: Int): BoardLines = createBoardLines(
        boardSize,
        minePositionsGenerator.generate(boardSize, mineCount),
    )

    private fun createBoardLines(
        boardSize: BoardSize,
        minePositions: List<Position>,
    ): BoardLines = BoardLines(List(boardSize.height) { row ->
        createBoardLine(boardSize, row, minePositions)
    })

    private fun createBoardLine(
        boardSize: BoardSize,
        row: Int,
        minePositions: List<Position>,
    ): BoardLine = BoardLine(List(boardSize.width) { column ->
        Cell.create(Position(row, column), boardSize, minePositions, aroundMineCountJudge)
    })

}
