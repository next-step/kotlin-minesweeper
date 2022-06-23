package domain

class Row(cells: List<Cell>) : List<Cell> by cells {
    constructor(vararg cell: Cell) : this(cell.toList())
}
