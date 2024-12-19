package minsweeper.domain

class BoardLinesGenerator(
    private val mineCoordinatesGenerator: MineCoordinatesGenerator = RandomMineCoordinatesGenerator(),
    private val aroundMineCountJudge: AroundMineCountJudge,
) {

    fun generate(boardSize: BoardSize, mineCount: Int): BoardLines = createBoardLines(
        boardSize,
        mineCoordinatesGenerator.generate(boardSize, mineCount),
    )

    private fun createBoardLines(
        boardSize: BoardSize,
        mineCoordinates: List<Coordinate>,
    ): BoardLines = BoardLines(List(boardSize.height) { row ->
        createBoardLine(boardSize, row, mineCoordinates)
    })

    private fun createBoardLine(
        boardSize: BoardSize,
        row: Int,
        mineCoordinates: List<Coordinate>,
    ): BoardLine = BoardLine(List(boardSize.width) { column ->
        Cell.create(Coordinate.of(row, column), boardSize, mineCoordinates, aroundMineCountJudge)
    })

}
