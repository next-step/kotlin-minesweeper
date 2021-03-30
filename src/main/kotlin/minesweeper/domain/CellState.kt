package minesweeper.domain

sealed class CellState {
    var open: Boolean = false
        private set

    abstract val count: Int

    open fun open() = turnOpen()

    fun turnOpen() {
        open = true
    }

    class Bomb : CellState() {
        override val count: Int = 0
    }

    class BombSide(override val count: Int) : CellState()

    class Blank(private val link: List<CellState> = emptyList()) : CellState() {
        override val count: Int = 0
        override fun open() {
            super.open()
            link.filterNot { it.open }
                .forEach { it.open() }
        }
    }
}
