package domain

class MineDetector(
    private val boardInfo: BoardInfo,
    private val board: Board
) {
    fun getMinesAroundCount(x: Int, y: Int): Int {
        return Directions.values().count { direction ->
            val targetX = x + direction.value.first
            val targetY = y + direction.value.second
            (boardInfo.isContainRange(targetX, targetY) && board.isMineCell(Coordinate.from(targetX, targetY)))
        }
    }
}