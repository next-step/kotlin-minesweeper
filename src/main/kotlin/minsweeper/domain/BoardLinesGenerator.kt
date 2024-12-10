package minsweeper.domain

object BoardLinesGenerator {

    fun generate(height: Int, width: Int, mineCount: Int): BoardLines {
        val minePositions = createMinePositions(height, width, mineCount)
        return BoardLines(List(height) { row ->
            BoardLine(List(width) { col ->
                val position = row * width + col
                if (position in minePositions) Cell.Mine else Cell.Island
            })
        })
    }

    private fun createMinePositions(height: Int, width: Int, mineCount: Int) =
        (0 until height * width).shuffled().take(mineCount)

}
