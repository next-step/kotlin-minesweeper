package minesweeper.domain

internal data class Cell(private val hasMine: Boolean = false) {
    val display: String = if (hasMine) "*" else "C"
}
