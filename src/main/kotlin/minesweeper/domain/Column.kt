package minesweeper.domain

class Column(private val cells: List<Cell>) : List<Cell> by cells {
    constructor(width: Width) : this(List(width.value) { Cell() })
}
