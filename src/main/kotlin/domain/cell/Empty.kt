package domain.cell

data class Empty(override val row: Int, override val column: Int, override val symbol: String = EMPTY) : Cell {
    companion object {
        const val EMPTY = "C"
    }
}
