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
        minePositions: List<Int>,
    ): BoardLines = BoardLines(List(boardSize.height) { row ->
        createBoardLine(boardSize, row, minePositions)
    })

    private fun createBoardLine(
        boardSize: BoardSize,
        row: Int,
        minePositions: List<Int>,
    ): BoardLine = BoardLine(List(boardSize.width) { col ->
        createCell(row, boardSize, col, minePositions)
    })

    private fun createCell(
        row: Int,
        boardSize: BoardSize,
        col: Int,
        minePositions: List<Int>,
    ): Cell {
        val position = row * boardSize.width + col
        return Cell.Mine.takeIf { position in minePositions } ?: Cell.Island(
            aroundMineCountJudge.judge(
                position,
                minePositions,
            )
        )

    }

}
