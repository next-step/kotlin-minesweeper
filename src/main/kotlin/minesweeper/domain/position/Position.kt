package minesweeper.domain.position

data class Position(
    val row: Int,
    val col: Int
) {
    init {
        require(row >= 0 && col >= 0) {
            "row and col should be greater or equal than 0 [row:$row, col:$col]"
        }
    }
}
