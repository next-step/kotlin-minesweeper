package minesweeper.domain

interface CellState {
    fun count(): Int = 0
    fun openLinkedCell() = Unit

    class Bomb : CellState

    class BombSide(private val count: Int) : CellState {
        override fun count(): Int = count
    }

    class Blank(private val link: List<Cell> = emptyList()) : CellState {
        override fun openLinkedCell() = link.forEach { it.open() }
    }

    class Open(cellState: CellState) : CellState by cellState {
        override fun openLinkedCell() = Unit
    }
}
