package domain

class BoardGenerator(private val gameInfo: GameInfo) {
    private val vertical = gameInfo.vertical.number
    private val horizontal = gameInfo.horizontal.number

    fun create(): Cells {
        val squares = createSquares()

        return (1..vertical).flatMap { yIdx ->
            (1..horizontal).map { xIdx ->
                Cell(Position.of(xIdx, yIdx), squares.removeFirst())
            }
        }.toList()
            .toCells()
    }

    private fun createSquares(): MutableList<Square> {
        val area = vertical * horizontal
        val mineCount = gameInfo.mineCount.number

        val mineList = List(mineCount) { Square.Mine }
        val noneMineList = List(area - mineCount) { Square.NonMine }

        return (mineList + noneMineList).shuffled().toMutableList()
    }
}
