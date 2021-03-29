package minesweeper.domain

sealed class CellState(val link: List<CellState>) {
    var open: Boolean = false
        private set

    abstract val count: Int
    abstract val bomb: Boolean

    open fun discover() = turnOpen()

    fun turnOpen() {
        open = true
    }

    class BombCell : CellState(emptyList()) {
        override val count: Int = 0
        override val bomb: Boolean = true
    }

    class BombSideCell(override val count: Int) : CellState(emptyList()) {
        override val bomb: Boolean = false
    }

    class BlankCell(link: List<CellState>) : CellState(link) {
        override val count: Int = 0
        override val bomb: Boolean = false
        override fun discover() {
            super.discover()
            link.filterNot { it.open || it.bomb }
                .forEach { it.discover() }
        }
    }
}
