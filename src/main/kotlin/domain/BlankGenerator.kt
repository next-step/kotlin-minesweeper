package domain

class BlankGenerator(
    override val locations: List<Int>,
    override val boardInfo: BoardInfo,
    private val board: Board
) : CellGenerator {
    override fun generate(): List<Blank> {
        return locations.map {
            val x = it / boardInfo.getRow() + 1
            val y = it % boardInfo.getRow() + 1
            Blank.from(x, y, searchMinesAroundCount(x, y))
        }
    }

    private fun searchMinesAroundCount(x: Int, y: Int): Int {
        return Directions.values().count { direction ->
            val targetX = x + direction.value.first
            val targetY = y + direction.value.second
            (boardInfo.isContainRange(targetX, targetY) && board.isMineCell(Coordinate.from(targetX, targetY)))
        }
    }
}
