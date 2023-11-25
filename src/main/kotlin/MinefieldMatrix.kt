data class MinefieldMatrix(val matrix: List<MinefieldRow>) {
    private val heightRange: IntRange
        get() = matrix.indices
    private val widthRange: IntRange
        get() = matrix[Const.FIRST_INDEX].mineFields.indices

    fun updateSurroundingMineCounts(
        height: Int,
        width: Int,
    ): MinefieldMatrix {
        val result = matrix.toMutableList()
        if (height in heightRange && width in widthRange) {
            result[height] = result[height].markAsMine(width)
            Const.ADJACENT_DIRECTIONS.forEach { point -> updateCellForMineCount(point, height, width, result) }
        }
        return MinefieldMatrix(result)
    }

    private fun updateCellForMineCount(
        point: Point,
        height: Int,
        width: Int,
        result: MutableList<MinefieldRow>,
    ) {
        val newHeight = height + point.x
        val newWidth = width + point.y
        if (newHeight !in heightRange || newWidth !in widthRange) return
        if (result[newHeight].isMine(newWidth)) return
        result[newHeight] = result[newHeight].incrementMineCountsIfAdjacent(newWidth)
    }

    fun getMap(): List<List<Int>> = matrix.map { it.mineFields }

    companion object {
        fun of(height: Int, width: Int): MinefieldMatrix =
            MinefieldMatrix((Const.START_INDEX until height).map { MinefieldRow.of(width) })
    }
}
