package domain

class Row(
    val cells: List<Cell>,
) {
    val width = cells.size

    fun isMine(position: Int): Boolean {
        return cells[position].isMine()
    }
}
