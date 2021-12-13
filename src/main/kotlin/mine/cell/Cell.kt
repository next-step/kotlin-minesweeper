package mine.cell

abstract class Cell(
    val position: Position,
) {
    var isClicked: Boolean = false

    open fun isNearMine(): Boolean = false

    open fun isMineCell(): Boolean = true

    fun aroundPosition(): List<Position> = position.aroundPosition()

    fun aroundAllPosition(): List<Position> = position.aroundAllPosition()

    fun isSamePosition(position: Position): Boolean = position.x == this.position.x && position.y == this.position.y

    fun open() {
        if (isMineCell() || isClicked) return
        isClicked = true
    }
}
