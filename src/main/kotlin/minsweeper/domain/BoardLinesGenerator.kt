package minsweeper.domain

class BoardLinesGenerator(
    private val minePositionsGenerator: MinePositionsGenerator = RandomMinePositionsGenerator(),
) {

    fun generate(boardSize: BoardSize, mineCount: Int): BoardLines = createBoardLines(
        boardSize,
        minePositionsGenerator.generate(boardSize.area, mineCount),
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
    ): Cell = Cell.Mine.takeIf { row * boardSize.width + col in minePositions } ?: Cell.Island

}
