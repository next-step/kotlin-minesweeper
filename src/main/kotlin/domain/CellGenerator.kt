package domain

interface CellGenerator {
    val locations: Locations
    val boardInfo: BoardInfo

    fun generate(): List<Cell>
}
