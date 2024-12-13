package minsweeper.domain

class BoardLinesGenerator(
    private val minePositionsGenerator: MinePositionsGenerator = DefaultMinePositionsGenerator(),
) {

    fun generate(boardParam: BoardParam): BoardLines {
        val minePositions = minePositionsGenerator.generate(boardParam)
        return createBoardLines(boardParam, minePositions)
    }

    private fun createBoardLines(
        boardParam: BoardParam,
        minePositions: List<Int>,
    ): BoardLines = BoardLines(List(boardParam.height) { row ->
        createBoardLine(boardParam, row, minePositions)
    })

    private fun createBoardLine(
        boardParam: BoardParam,
        row: Int,
        minePositions: List<Int>,
    ): BoardLine = BoardLine(List(boardParam.width) { col ->
        createCell(row, boardParam, col, minePositions)
    })

    private fun createCell(
        row: Int,
        boardParam: BoardParam,
        col: Int,
        minePositions: List<Int>,
    ): Cell = Cell.Mine.takeIf { row * boardParam.width + col in minePositions } ?: Cell.Island

}
