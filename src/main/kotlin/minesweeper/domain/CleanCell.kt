package minesweeper.domain

private const val CLEAN_SIGN = "C"

data class CleanCell(override val state: String = CLEAN_SIGN) : Cell {
}
