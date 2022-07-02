package domain

data class Cells(
    private val cells: List<Cell>
) {
    fun get() = cells.toList()

    fun getCell(x: Int, y: Int): Cell =
        cells.first { it.position.x.toInt() == x && it.position.y.toInt() == y }
}

fun List<Cell>.toCells() = Cells(this)
