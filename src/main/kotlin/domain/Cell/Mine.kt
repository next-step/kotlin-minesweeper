package domain.Cell

data class Mine(override val row: Int, override val column: Int, override val symbol: String = MINE) : Cell {
    companion object {
        const val MINE = "*"
    }
}
