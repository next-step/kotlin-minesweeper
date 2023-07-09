package domain.cell

fun List<Cell>.toCells() = Cells(this)
fun Cells.filtered(condition: (cell: Cell) -> Boolean): Cells {
    return value.filter { condition(it) }.toCells()
}

class Cells(val value: List<Cell>) {
    fun getCleanAroundMineCount(): Cells {
        return value.filter { it.property.isCleanAroundMineCount() }.toCells()
    }
}
