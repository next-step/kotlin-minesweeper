package minesweeper.cell

import minesweeper.position.Position

data class Cell(
    private val position: Position,
    private var status: CellType,
    private var value: Char
) {

    fun isMine(): Boolean = this.status == CellType.MINE

    fun isVisited(): Boolean = this.status == CellType.VISITED

    fun visit() {
        this.status = CellType.VISITED
    }

    fun changeValue(other: Cell) {
        this.value = other.value
    }

    fun increaseValue() {
        value++
    }

    override fun toString(): String = this.value.toString()
}
