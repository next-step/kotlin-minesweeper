class MineSweeper(
    private val width: Int,
    private val height: Int,
    mineSize: Int,
    generateMinePositions: (Int) -> List<Position>
) {
    private val minePositions = generateMinePositions(mineSize)

    fun cells(): Map<Position, Status> {
        return Position.createInRange(width, height)
            .associateWith { cellValue(it) }
    }

    private fun cellValue(position: Position): Status {
        return if (minePositions.contains(position)) {
            Status.MINE
        } else {
            Status.EMPTY
        }
    }
}
