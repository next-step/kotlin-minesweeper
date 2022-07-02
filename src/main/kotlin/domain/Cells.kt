package domain

data class Cells(
    private val cells: List<Cell>
)

fun List<Cell>.toCells() = Cells(this)
