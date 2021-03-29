package minesweeper.domain

sealed class CellState {
    var open: Boolean = false
        private set

    abstract val count: Int
    abstract val bomb: Boolean

    open fun discover() = turnOpen()

    fun turnOpen() {
        open = true
    }

    class BombCell : CellState() {
        override val count: Int = 0
        override val bomb: Boolean = true
    }

    class BombSideCell(override val count: Int) : CellState() {
        override val bomb: Boolean = false
    }

    class BlankCell(private val link: List<CellState> = emptyList()) : CellState() {
        override val count: Int = 0
        override val bomb: Boolean = false
        override fun discover() {
            super.discover()
            link.filterNot { it.open }
                .forEach { it.discover() }
        }
    }
}
