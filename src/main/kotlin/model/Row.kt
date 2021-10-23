package model

import dto.RowDTO

data class Row(
    private val cells: List<Cell>
) {
    constructor(vararg cells: Cell) : this(cells.asList())

    fun get(width: Int): Cell {
        return cells[width]
    }

    fun containingExploded(): Boolean {
        return cells.any {
            it.exploded()
        }
    }

    fun clean(): Boolean {
        return cells.all {
            (it.opened() && it.safe()) || (it.closed() && !it.safe())
        }
    }

    fun asDTO(): RowDTO {
        return RowDTO(
            cells.map { it.asDTO() }
        )
    }
}