package minesweeper.domain

sealed class CellState {
    var open: Boolean = false
        private set

    abstract val count: Int

    open fun discover() = turnOpen()

    fun turnOpen() {
        open = true
    }

    class BombCell : CellState() {
        override val count: Int = 0
    }

    class BombSideCell(override val count: Int) : CellState()

    class BlankCell(private val link: List<CellState> = emptyList()) : CellState() {
        override val count: Int = 0
        override fun discover() {
            super.discover()
            link.filterNot { it.open }
                .forEach { it.discover() }
        }
    }
}
