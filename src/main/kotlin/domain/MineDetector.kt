package domain

class MineDetector(
    private val boardInfo: BoardInfo,
    private val board: Board
) {
    fun getMinesAroundCount(x: Int, y: Int): Int {
        return Directions.values().count { direction ->
            val targetX = x + direction.value.first
            val targetY = y + direction.value.second
            isCountMine(targetX, targetY)
        }
    }

    private fun isCountMine(targetX: Int, targetY: Int): Boolean {
        return (boardInfo.isContainRange(targetX, targetY) && board.isMineCell(Coordinate.from(targetX, targetY)))
    }
}
