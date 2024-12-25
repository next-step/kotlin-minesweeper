package minesweeper.domain

class Field(
    val fieldInfo: FieldInfo,
    private val minePositions: Set<Position>,
) {
    private val spots: Map<Position, Spot> = createField()

    init {
        validateMineCount()
    }

    private fun createField(): Map<Position, Spot> {
        return (0 until fieldInfo.getWidth() + 1).flatMap { x ->
            (0 until fieldInfo.getHeight() + 1).map { y ->
                Position(x, y)
            }
        }.associateWith { position ->
            when {
                minePositions.contains(position) -> MineSpot(position)
                else -> SafeSpot(position)
            }
        }
    }

    fun getSpot(position: Position): Spot {
        return spots[position] ?: throw IllegalArgumentException("해당 위치에 대한 Spot이 존재하지 않습니다.")
    }

    private fun validateMineCount() {
        val totalPossibleSpots = fieldInfo.getHeight() * fieldInfo.getWidth()
        require(minePositions.size <= totalPossibleSpots) { "지뢰 개수는 필드의 총 스팟보다 많을 수 없습니다." }
    }

    fun openSpot(position: Position): OpenResult {
        val targetSpot =
            spots[position]?.let {
                if (it.isMine()) {
                    return OpenResult.GameOver
                }
                it
            } as SafeSpot
        val openResult = targetSpot.open()
        targetSpot.calculateNearbyMineCount(minePositions)
        checkAndOpenNearbySpot(targetSpot)
        return openResult
    }

    private fun checkAndOpenNearbySpot(targetSpot: SafeSpot) {
        if (targetSpot.nearbyMineCount == 0) {
            openNearbySpots(targetSpot.position)
        }
    }

    private fun openNearbySpots(position: Position) {
        val nearbyPositions = position.nearbyPositions()
        nearbyPositions.forEach {
            spots[it]?.let { spot ->
                if (!spot.isOpened()) {
                    openSpot(it)
                }
            }
        }
    }
}
