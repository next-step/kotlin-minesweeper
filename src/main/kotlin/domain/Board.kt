package domain

/**
 * 지뢰찾기 판
 * Created by Jaesungchi on 2022.06.28..
 */
data class Board(
    val grounds: Map<Position, Ground>,
    private val gameSettingInfo: GameSettingInfo
) {
    init {
        setAutoMine()
    }

    private fun setAutoMine() {
        grounds.toList().shuffled().take(gameSettingInfo.mineCount).map {
            grounds[it.first]?.installMine()
            setMineCount(it.first)
        }
    }

    fun setManualMine(positions: List<Position>?) {
        positions?.forEach {
            grounds[it]?.installMine()
            setMineCount(it)
        }
    }

    private fun setMineCount(position: Position) {
        repeat(ARROW_COUNT) {
            val tempX = position.x + X_MARGINS[it]
            val tempY = position.y + Y_MARGINS[it]
            val position = Position.makePositionOrNull(tempX, tempY, gameSettingInfo.width, gameSettingInfo.height)
                ?: return@repeat
            grounds[position]?.addMineCount()
        }
    }

    companion object {
        const val ARROW_COUNT = 8
        val Y_MARGINS = listOf(-1, -1, -1, 0, 0, 1, 1, 1)
        val X_MARGINS = listOf(-1, 0, 1, -1, 1, -1, 0, 1)
    }
}
