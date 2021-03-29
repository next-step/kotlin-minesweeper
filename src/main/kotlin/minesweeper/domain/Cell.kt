package minesweeper.domain

interface Cell {
    var open: Boolean
    var exploded: Boolean
    val bomb: Boolean
    val count: Int
    fun open()
    fun quietlyOpen()
    fun done(): Boolean
}

class CellWithState(private val cellState: CellState) : Cell {
    override var open: Boolean
        get() = cellState.open
        set(value) = TODO()
    override var exploded: Boolean
        get() = this.open && cellState.bomb
        set(value) = TODO()
    override val bomb: Boolean = cellState.bomb
    override val count: Int = cellState.count

    override fun open() {
        cellState.discover()
    }

    override fun quietlyOpen() {
        cellState.turnOpen()
    }

    override fun done(): Boolean {
        return cellState.open || cellState.bomb
    }
}

data class CellLegacy(override val bomb: Boolean = false, override val count: Int = 0) : Cell {
    lateinit var link: List<CellLegacy>
    override var open: Boolean = false
    override var exploded: Boolean = false

    override fun open() {
        if (bomb) {
            exploded = true
            open = true
            return
        }

        open = true

        if (count > 0) {
            return
        }

        link.filter { it.canOpen() }
            .forEach { it.open() }
    }

    override fun quietlyOpen() {
        open = true
    }

    override fun done(): Boolean {
        return open || bomb
    }

    private fun canOpen(): Boolean = !(bomb || open)
}
