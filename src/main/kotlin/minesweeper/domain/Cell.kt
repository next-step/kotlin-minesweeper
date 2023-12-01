package minesweeper.domain

class Cell(var isMine: Boolean = false) {
    override fun toString(): String {
        return when {
            isMine -> "*"
            else -> "C"
        }
    }
}
