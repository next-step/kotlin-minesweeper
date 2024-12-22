package minesweeper.domain

class Field(
    private val fieldInfo: FieldInfo,
    private val minePositions: Set<Position>,
) {
    private val spots: Map<Position, Spot> = createField()

    init {
        validateMineCount()
    }

    private fun createField(): Map<Position, Spot> {
        val spots = mutableMapOf<Position, Spot>()
        for (x in 0 until fieldInfo.getWidth() + 1) {
            for (y in 0 until fieldInfo.getHeight() + 1) {
                val position = Position(x, y)
                spots[position] =
                    if (minePositions.contains(position)) {
                        MineSpot(position)
                    } else {
                        SafeSpot(position, getNearByMineCount(minePositions, position))
                    }
            }
        }
        return spots
    }

    private fun getNearByMineCount(
        minePositions: Set<Position>,
        position: Position,
    ): Int {
        return NearbyDirection.entries
            .toTypedArray()
            .count { direction ->
                val nearbyPosition =
                    Position(
                        position.x + direction.dx(),
                        position.y + direction.dy(),
                    )
                minePositions.contains(nearbyPosition)
            }
    }

    fun getFieldInfo(): FieldInfo {
        return fieldInfo
    }

    fun getSpot(position: Position): Spot {
        return spots[position] ?: throw IllegalArgumentException("해당 위치에 대한 Spot이 존재하지 않습니다.")
    }

    private fun validateMineCount() {
        val totalPossibleSpots = fieldInfo.getHeight() * fieldInfo.getWidth()
        require(minePositions.size <= totalPossibleSpots) { "지뢰 개수는 필드의 총 스팟보다 많을 수 없습니다." }
    }
}
