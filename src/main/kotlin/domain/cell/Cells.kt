package domain.cell

fun List<Cell>.toCells() = Cells(this)

class Cells(val value: List<Cell>) {
    fun getCleanAroundMineCount(): Cells {
        return value.filter { it.property.isCleanAroundMineCount() }.toCells()
    }

    fun filter(condition: (cell: Cell) -> Boolean): Cells {
        return value.filter { condition(it) }.toCells()
    }
}
