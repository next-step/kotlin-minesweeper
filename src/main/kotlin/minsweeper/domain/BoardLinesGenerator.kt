package minsweeper.domain

class BoardLinesGenerator(
    private val minePositionsGenerator: MinePositionsGenerator = DefaultMinePositionsGenerator(),
) {

    fun generate(boardParam: BoardParam): BoardLines {
        val minePositions = minePositionsGenerator.generate(boardParam)
        return BoardLines(List(boardParam.height) { row ->
            BoardLine(List(boardParam.width) { col ->
                val position = row * boardParam.width + col

                if (position in minePositions) Cell.Mine
                else Cell.Island
            })
        })
    }

}
