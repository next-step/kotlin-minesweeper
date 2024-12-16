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
        createCell(row, boardSize, column, minePositions)
    })

    private fun createCell(
        row: Int,
        boardSize: BoardSize,
        column: Int,
        minePositions: List<Position>,
    ): Cell {
        val position = Position(row, column)
        return Cell.Mine.takeIf { position in minePositions } ?: Cell.Island(
            aroundMineCountJudge.judge(
                boardSize,
                position,
                minePositions,
            )
        )

    }

}
