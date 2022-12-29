package domain

class MineDetector(
    private val boardInfo: BoardInfo,
    private val board: Board
) {
    fun getMinesAroundCount(coordinate: Coordinate): Int {
        return Directions.values().count { direction ->
            val targetX = coordinate.x + direction.coordinate.x
            val targetY = coordinate.y + direction.coordinate.y
            isCountMine(Coordinate(targetX, targetY))
        }
    }

    private fun isCountMine(coordinate: Coordinate): Boolean {
        return boardInfo.isContainRange(coordinate) && board.isMineCell(coordinate)
    }
}
