package minesweeper.domain

data class Cell(
    val position: Int,
    val isMine: Boolean
) {

    override fun toString(): String {
        if (isMine) {
            return "⎈ "
        }
        return "C "
    }
}
