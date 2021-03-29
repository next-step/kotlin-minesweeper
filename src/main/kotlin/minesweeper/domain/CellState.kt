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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CellState

        if (count != other.count) return false
        if (bomb != other.bomb) return false

        return true
    }

    override fun hashCode(): Int {
        var result = count
        result = 31 * result + bomb.hashCode()
        return result
    }

    class BombCell : CellState(emptyList()) {
        override val count: Int = 0
        override val bomb: Boolean = true
    }

    class BombSideCell(override val count: Int) : CellState(emptyList()) {
        override val bomb: Boolean = false
    }

    class BlankCell(link: List<CellState> = emptyList()) : CellState(link) {
        override val count: Int = 0
        override val bomb: Boolean = false
        override fun discover() {
            super.discover()
            link.filterNot { it.open || it.bomb }
                .forEach { it.discover() }
        }
    }
}
