package minesweeper.domain

class Board private constructor(
    val rows: List<Row>,
) {
    fun isMineCell(x: Int, y: Int): Boolean {
        val cell = rows[x][y]
        return cell.hasMine()
    }

    fun countMinesNearby(x: Int, y: Int): Int {
        val cell = rows[x][y]
        return cell.countMinesNearBy
    }

    companion object {
        fun create(height: Int, width: Int, minesCoordinates: Coordinates): Board {
            val rows = createRows(height, width, minesCoordinates)
            return Board(rows)
        }

        private fun createRows(height: Int, width: Int, minesCoordinates: Coordinates): List<Row> {
            val initialCells = initialRows(height, width)

            plantMines(minesCoordinates, initialCells)

            val rows = initialCells.map { cells ->
                val rowCells = cells.map { cell ->
                    val countMines = countMinesNearby(cell, initialCells)
                    val state = initialState(minesCoordinates, cell.coordinate)

                    Cell.of(cell.coordinate.x, cell.coordinate.y, countMines, state)
                }
                Row.create(rowCells)
            }
            return rows
        }

        private fun initialState(minesCoordinates: Coordinates, coordinate: Coordinate): CellState {
            if (minesCoordinates.contains(coordinate)) {
                return MineState
            }

            return EmptyState
        }

        private fun initialRows(height: Int, width: Int): List<Row> {
            return List(height) { rowIndex ->
                val cells = initialCells(width, rowIndex)
                Row.create(cells)
            }
        }

        private fun initialCells(width: Int, rowIndex: Int): List<Cell> {
            return List(width) { columnIndex ->
                Cell.of(rowIndex, columnIndex)
            }
        }

        private fun countMinesNearby(cell: Cell, allCells: List<List<Cell>>): Int {
            val height = allCells.size
            val width = allCells.first().size
            val cellCoordinate = cell.coordinate

            var count = 0

            for (direction in Direction.values()) {
                val neighborX = cellCoordinate.x + direction.xMove
                val neighborY = cellCoordinate.y + direction.yMove
                val neighborCoordinate = Coordinate.of(neighborX, neighborY)

                if (!neighborCoordinate.checkWithinBounds(height, width)) {
                    continue
                }

                if (allCells[neighborX][neighborY].hasMine()) {
                    count++
                }
            }

            return count
        }

        private fun plantMines(minesCoordinates: Coordinates, rows: List<Row>) {
            minesCoordinates.forEach { coordinate ->
                val cell = rows[coordinate.x][coordinate.y]
                cell.plantMine()
            }
        }
    }
}
