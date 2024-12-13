package minsweeper.domain

class BoardLinesGenerator(
    private val minePositionsGenerator: MinePositionsGenerator = DefaultMinePositionsGenerator(),
) {

    fun generate(boardParam: BoardParam): BoardLines {
        val minePositions = minePositionsGenerator.generate(boardParam)
        return BoardLines(List(boardParam.height) { row ->
            BoardLine(List(boardParam.width) { col ->
                Cell.Mine.takeIf { row * boardParam.width + col in minePositions } ?: Cell.Island
            })
        })
    }

}
