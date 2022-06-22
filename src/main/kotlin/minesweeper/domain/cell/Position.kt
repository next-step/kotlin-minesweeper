package minesweeper.domain.cell

data class Position(
    val x: Int,
    val y: Int
) {
    init {
        require(x >= 0 && y >= 0) { "property must be zero or positive." }
    }
}
