package minesweeper.domain

class Board(width: Int, length: Int, mines: Int = 0) {
    private val coordinates = Coordinates(width, length)
    private val points = Points(coordinates, coordinates.makeMineCoordinates(mines))
    var isPlaying = true
        private set

    fun findPoint(x: Int, y: Int): Point = points.findPoint(x, y)

    fun forEachPoints(f: (point: Point) -> Unit) {
        points.forEach { f(it) }
    }

    fun openPoint(coordinate: Coordinate) {
        val point = findPoint(coordinate.x, coordinate.y)
        point.openPoint()
        endGame { point.isMine() || isPlayerWin() }
        if (point.mineCount == OPEN_AROUND_NUMBER) {
            findAroundCoordinate(point)
        }
    }

    private fun findAroundCoordinate(point: Point) {
        Direction.values().forEach {
            val coordinate = point.coordinate move it
            findAroundPoint(coordinate)
        }
    }

    private fun findAroundPoint(coordinate: Coordinate) {
        try {
            val point = findPoint(coordinate.x, coordinate.y)
            returnOpenPoint(point, coordinate)
        } catch (e: Exception) {
        }
    }

    private fun returnOpenPoint(point: Point, coordinate: Coordinate) {
        if (!point.isOpen) {
            openPoint(coordinate)
        }
    }

    private fun endGame(f: () -> Boolean) {
        if (f()) {
            isPlaying = false
        }
    }

    fun isPlayerWin(): Boolean = points.getNotOpenPoints().isEmpty()

    companion object {
        const val OPEN_AROUND_NUMBER = 0
    }
}
