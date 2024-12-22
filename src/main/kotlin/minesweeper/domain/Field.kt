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
                val x = index % width
                val y = index / width
                val nearbyMineCount = countAdjacentMines(spots, x, y)
                spot.updateNearbyMineCount(nearbyMineCount)
            }
            spot
        }.chunked(width).map { lineSpots ->
            FieldLine(lineSpots)
        }
    }

    private fun countAdjacentMines(
        spots: List<Spot>,
        x: Int,
        y: Int,
    ): Int {
        return NearbyDirection.entries
            .toTypedArray()
            .count { direction ->
                val newX = x + direction.dx()
                val newY = y + direction.dy()
                isWithinBounds(newX, newY) && spots[newY * width + newX].isMine()
            }
    }

    private fun isWithinBounds(
        x: Int,
        y: Int,
    ): Boolean {
        return x in 0 until width && y in 0 until fieldInfo.getHeight()
    }

    private fun validateMineCount() {
        val height = fieldInfo.getHeight()
        val totalSpots = height * width
        require(mineCount.count <= totalSpots) { "지뢰 개수는 필드의 총 스팟보다 많을 수 없습니다." }
    }
}
