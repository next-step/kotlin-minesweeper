package minesweeper.domain

class MineMap(private val height: Height, private val width: Width) {

    fun createRow(y: Int): List<Position> {
        return (1..width.value).map { Position(it, y) }
    }

    fun isInMap(position: Position): Boolean {
        return position.x in 1..width.value && position.y in 1..height.value
    }

    fun createPosition(): List<Position> {
        val yList = List(height.value) { it + Height.MINIMUM_HEIGHT }
        val xList = List(width.value) { it + Width.MINIMUM_WIDTH }
        return yList.flatMap { y ->
            createMapPosition(xList, y)
        }
    }

    private fun createMapPosition(xList: List<Int>, y: Int): List<Position> {
        return xList.map { x ->
            Position(x, y)
        }
    }
}
