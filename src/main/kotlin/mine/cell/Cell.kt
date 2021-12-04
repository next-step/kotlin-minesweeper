package mine.cell

abstract class Cell(val position: Position) {
    abstract fun name(): String

    fun aroundPosition(): List<Position> {
        val xList = listOf(position.x - 1, position.x, position.x + 1).filter { it > 0 }
        val yList = listOf(position.y - 1, position.y, position.y + 1).filter { it > 0 }
        return xList
            .flatMap { x ->
                yList.map { Position(x, it) }
            }
            .filterNot { it == position }
    }
}
