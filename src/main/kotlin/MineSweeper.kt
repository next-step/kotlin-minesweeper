class MineSweeper(
    private val width: Int,
    private val height: Int,
    mineSize: Int,
    generateMinePositions: (Int) -> List<Position>
) {
    private val minePositions = generateMinePositions(mineSize)

    fun cells(): Map<Position, Status> {
        return allPositions().associateWith { cellValue(it) }
    }

    private fun allPositions(): List<Position> {
        return (0 until width).flatMap { x ->
            (0 until height).map { y ->
                Position(y, x)
            }
        }
    }

    private fun cellValue(position: Position): Status {
        return if (minePositions.contains(position)) {
            Status.MINE
        } else {
            Status.EMPTY
        }
    }
}
