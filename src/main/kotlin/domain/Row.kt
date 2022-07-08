package domain

class Row(cells: MutableList<Cell>) : MutableList<Cell> by cells {
    constructor(vararg cell: Cell) : this(cell.toMutableList())
}
