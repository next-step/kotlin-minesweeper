package minesweeper

data class Cell(
    val height: Height,
    val width: Width,
    var isMine: Boolean = false,
) {
    override fun toString(): String {
        return if (isMine) {
            "*"
        } else {
            "C"
        }
    }
}
