package domain

interface CellGenerator {
    val locations: List<Int>
    val row: Row

    fun generate(): List<Cell>
}
