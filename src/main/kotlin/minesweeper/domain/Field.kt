package minesweeper.domain

class Field(
    private val fieldInfo: FieldInfo,
    private val mineCount: MineCount,
    private val spotGenerator: SpotGenerator,
) {
    private val width = fieldInfo.getWidth()
    val lines: List<FieldLine> = createField()

    init {
        validateMineCount()
    }

    private fun createField(): List<FieldLine> {
        val spots = spotGenerator.generate(fieldInfo, mineCount)
        return spots.mapIndexed { index, spot ->
            if (spot is SafeSpot) {
                val y = index / width
                val x = index % width
                val nearbyMineCount = countAdjacentMines(spots, y, x)
                spot.updateNearbyMineCount(nearbyMineCount)
            }
            spot
        }.chunked(width).map { lineSpots ->
            FieldLine(lineSpots)
        }
    }

    private fun countAdjacentMines(
        spots: List<Spot>,
        y: Int,
        x: Int,
    ): Int {
        return NEARBY.count { (dy, dx) ->
            val newY = y + dy
            val newX = x + dx
            isWithinBounds(newY, newX) && spots[newY * width + newX].isMine()
        }
    }

    private fun isWithinBounds(
        y: Int,
        x: Int,
    ): Boolean {
        return y in 0 until fieldInfo.getHeight() && x in 0 until width
    }

    private fun validateMineCount() {
        val height = fieldInfo.getHeight()
        val totalSpots = height * width
        require(mineCount.count <= totalSpots) { "지뢰 개수는 필드의 총 스팟보다 많을 수 없습니다." }
    }

    companion object {
        private val NEARBY =
            listOf(
                Pair(-1, 0),
                Pair(0, -1),
                Pair(0, 1),
                Pair(1, 0),
            )
    }
}
