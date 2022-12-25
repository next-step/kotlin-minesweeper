package domain

interface CellGenerator {
    val locations: List<Int>
    val boardInfo: BoardInfo

    fun generate(): List<Cell>
}
