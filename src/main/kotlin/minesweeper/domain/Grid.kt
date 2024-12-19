package minesweeper.domain

class Grid(
    private val dimension: Dimension,
    private val mineCount: MineCount,
    private val mineGenerator: MineGenerator,
) {
    val cells: List<List<Cell>> = initializeCells()

    private fun initializeCells(): List<List<Cell>> {
        val totalCells = dimension.totalCells()
        val minePositions = mineGenerator.generateMinePositions(totalCells, mineCount.count)
        val tempCells = createInitialCells(minePositions)  // Initial mine placement
        return mapAdjacentCounts(tempCells)
    }

    private fun createInitialCells(minePositions: Set<Int>): List<List<Cell>> {
        return (0 until dimension.height).map { row ->
            (0 until dimension.width).map { col ->
                createInitialCell(row, col, minePositions)
            }
        }
    }

    private fun createInitialCell(
        row: Int,
        col: Int,
        minePositions: Set<Int>
    ): Cell {
        val currentPosition = row * dimension.width + col
        val isMine = currentPosition in minePositions
        return if (isMine) {
            Cell(Mine())
        } else {
            Cell(Empty())
        }
    }

    private fun mapAdjacentCounts(tempCells: List<List<Cell>>): List<List<Cell>> {
        return tempCells.mapIndexed { row, rowCells ->
            rowCells.mapIndexed { col, cell ->
                createAdjacentCountCell(cell, row, col, tempCells)
            }
        }
    }

    private fun createAdjacentCountCell(
        cell: Cell,
        row: Int,
        col: Int,
        tempCells: List<List<Cell>>
    ): Cell {
        return if (cell.isMine()) {
            cell
        } else {
            val count = countAdjacentMines(row, col, tempCells)
            Cell(Empty(count))
        }
    }

    private fun countAdjacentMines(row: Int, col: Int, cells: List<List<Cell>>): Int {
        fun isWithinBounds(row: Int, col: Int, cells: List<List<Cell>>) =
            row in cells.indices && col in cells[row].indices

        return (-1..1).sumOf { dx ->
            (-1..1).count { dy ->
                val isCurrentCell = dx == 0 && dy == 0
                val adjacentRow = row + dx
                val adjacentCol = col + dy
                val isValidCell = isCurrentCell.not() && isWithinBounds(adjacentRow, adjacentCol, cells)
                val isAdjacentCellMine = isValidCell && cells[adjacentRow][adjacentCol].isMine()
                isAdjacentCellMine
            }
        }
    }
}
