package domain

class Row(
    private val cells: List<Cell>
) : List<Cell> by cells
