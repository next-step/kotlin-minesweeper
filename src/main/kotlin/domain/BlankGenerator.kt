package domain

class BlankGenerator(
    override val locations: Locations,
    override val boardInfo: BoardInfo,
    private val board: Board
) : CellGenerator {
    override fun generate(): List<Blank> {
        val mineDetector = MineDetector(boardInfo, board)

        return locations.values.map {
            val x = it / boardInfo.getRow() + 1
            val y = it % boardInfo.getRow() + 1
            val minesAroundCount = mineDetector.getMinesAroundCount(x, y)

            Blank.from(x, y, minesAroundCount)
        }
    }
}
