package mine.cell

abstract class Cell(
    val position: Position,
) {
    open fun name(): String = NAME

    fun aroundPosition(): List<Position> = position.aroundPosition()

    fun isSamePosition(position: Position): Boolean = position.x == this.position.x && position.y == this.position.y

    companion object {
        const val NAME = "E"
    }
}
