package domain

class BoardGenerator(private val gameInfo: GameInfo) {
    private val vertical = gameInfo.vertical.toInt()
    private val horizontal = gameInfo.horizontal.toInt()

    fun create(): List<Cell> {
        val squares = createSquares().toMutableList()

        return (1..vertical).flatMap { yIdx ->
            (1..horizontal).map { xIdx ->
                Cell(Position.of(xIdx, yIdx), squares.removeFirst())
            }
        }.toList()
    }

    private fun createSquares(): List<Square> {
        val area = vertical * horizontal
        val mineCount = gameInfo.mineCount.toInt()

        val mineList = List(mineCount) { Square.Mine }
        val noneMineList = List(area - mineCount) { Square.NonMine }

        return (mineList + noneMineList).shuffled()
    }
}
