package minesweeper.domain.cell

open class Cell(
    val position: Position
) {
    open fun isMine() = false

    override fun equals(other: Any?) =
        when (other) {
            is Cell -> isPosition(other.position)
            is Position -> isPosition(other)
            else -> false
        }

    fun getAroundPositions() = position.getAroundPositions()

    fun open(aroundMineCount: Int): Cell =
        if (isMine()) this else NumberCell(aroundMineCount, position)

    fun isPosition(position: Position) = this.position == position

    override fun toString() = "\uD83C\uDF2B"

    override fun hashCode(): Int {
        return position.hashCode()
    }
}

fun Position.toCell() = Cell(this)
