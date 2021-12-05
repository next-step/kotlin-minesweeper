package mine.cell

abstract class Cell(
    val position: Position,
) {
    abstract fun name(): String

    fun aroundPosition(): List<Position> = position.aroundPosition()

    fun isSamePosition(position: Position): Boolean = position.x == this.position.x && position.y == this.position.y
}
