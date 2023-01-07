package domain

class MineDetector(
    private val boardInfo: BoardInfo,
    private val board: Board
) {
    fun getMinesAroundCount(coordinate: Coordinate): Int {
        return Directions.values().count { direction ->
            val targetCoordinate = coordinate.movedCoordinate(direction)
            isCountMine(targetCoordinate)
        }
    }

    private fun isCountMine(coordinate: Coordinate): Boolean {
        return boardInfo.isContainRange(coordinate) && board.isMineCell(coordinate)
    }
}
