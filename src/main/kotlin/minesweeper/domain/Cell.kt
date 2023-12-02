package minesweeper.domain

data class Cell(val point: Point, var state: State) : Comparable<Cell> {
    override fun compareTo(other: Cell): Int {
        return compareValuesBy(this, other, { it.point.x }, { it.point.y })
    }
}
