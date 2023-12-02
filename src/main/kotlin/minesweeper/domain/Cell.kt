package minesweeper.domain

class Cell(val point: Point, val state: State) : Comparable<Cell> {
    override fun compareTo(other: Cell): Int {
        return compareValuesBy(this, other, { it.point.x }, { it.point.y })
    }
}
