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
            val initialRows = initialRows(height, width)
            plantMines(minesCoordinates, initialRows)

            val countedMinesRows = initialRows.map { cells ->
                countedMinesRows(cells, initialRows, minesCoordinates)
            }
            return Board(countedMinesRows)
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

        private fun plantMines(minesCoordinates: Coordinates, rows: List<Row>) {
            minesCoordinates.forEach { coordinate ->
                val cell = rows[coordinate.x][coordinate.y]
                cell.plantMine()
            }
        }

        private fun countedMinesRows(cells: Row, initialRows: List<Row>, minesCoordinates: Coordinates): Row {
            val rowCells = cells.map { cell ->
                val cellCoordinate = cell.coordinate
                val countMines = countMinesNearby(cell, initialRows)
                val state = initialState(minesCoordinates, cellCoordinate)

                Cell.of(cellCoordinate.x, cellCoordinate.y, countMines, state)
            }
            return Row.create(rowCells)
        }

        private fun initialState(minesCoordinates: Coordinates, coordinate: Coordinate): CellState {
            if (minesCoordinates.contains(coordinate)) {
                return MineState
            }

            return EmptyState
        }

        private fun countMinesNearby(cell: Cell, initialRows: List<Row>): Int {
            val height = initialRows.size
            val width = initialRows.first().size
            val cellCoordinate = cell.coordinate

            var count = 0

            for (direction in Direction.values()) {
                val neighborX = cellCoordinate.x + direction.xMove
                val neighborY = cellCoordinate.y + direction.yMove
                val neighborCoordinate = Coordinate.of(neighborX, neighborY)

                if (!neighborCoordinate.checkWithinBounds(height, width)) {
                    continue
                }

                if (initialRows[neighborX][neighborY].hasMine()) {
                    count++
                }
            }

            return count
        }
    }
}
