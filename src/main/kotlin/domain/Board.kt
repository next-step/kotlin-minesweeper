package domain

class Board(cells: List<Cell>) {
    val cells: List<Cell>

    init {
        this.cells = cells.sortedWith(compareBy({ it.coordinate.x.value }, { it.coordinate.y.value }))
    }

    companion object {
        fun from(row: Row, column: Column, mineCount: MineCount): Board {
            val cellSize = row * column

            val locations = List(cellSize) { it }
            val randomMineLocations = (0 until cellSize).shuffled().take(mineCount.value)
            val blankLocations = locations - randomMineLocations.toSet()
            val cellGenerator = CellGenerator(blankLocations, randomMineLocations, row)

            return Board(cellGenerator.minesGenerate() + cellGenerator.blanksGenerate())
        }
    }
}
