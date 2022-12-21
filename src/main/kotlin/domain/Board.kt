package domain

class Board(cells: List<Cell>) {
    init {
        cells.sortedWith(compareBy({ it.coordinate.x.value }, { it.coordinate.y.value })).toList()
    }
    private val _cells: MutableList<Cell> = cells.toMutableList()
    val cells: List<Cell>
        get() = _cells.toList()

    companion object {
        fun from(row: Row, column: Column, mineCount: MineCount): Board {
            val cellSize = row.value * column.value

            val locations = List(cellSize) { it }
            val randomMineLocations = (0 until cellSize).shuffled().take(mineCount.value)
            val blankLocations = locations - randomMineLocations.toSet()

            val mineCells = makeMineCells(randomMineLocations, row)
            val blankCells = makeBlankCells(blankLocations, row)

            return Board(mineCells + blankCells)
        }

        private fun makeMineCells(cells: List<Int>, row: Row): List<Cell> {
            return cells.map {
                Mine.from(it / row.value + 1, it % row.value + 1)
            }
        }

        private fun makeBlankCells(cells: List<Int>, row: Row): List<Cell> {
            return cells.map {
                Blank.from(it / row.value + 1, it % row.value + 1)
            }
        }
    }
}
