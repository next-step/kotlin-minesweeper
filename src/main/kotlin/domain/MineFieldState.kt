package domain

data class MineFieldState(
    val cells: Cells,
) {
    fun areAllNonMinesOpened(): Boolean {
        return cells.areAllNonMinesOpened()
    }
}
