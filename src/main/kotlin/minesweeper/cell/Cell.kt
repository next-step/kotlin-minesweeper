package minesweeper.cell

import minesweeper.position.Position

data class Cell(
    private val position: Position,
    private val status: CellType,
    private var value: Char
) {

    fun isMine(): Boolean = this.status == CellType.MINE

    fun increaseValue() {
        value++
    }

    override fun toString(): String {
        return "Cell(position=$position, status=$status, value=$value)"
    }
}
