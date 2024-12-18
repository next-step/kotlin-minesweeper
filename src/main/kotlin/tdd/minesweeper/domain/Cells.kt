package tdd.minesweeper.domain

import tdd.minesweeper.domain.strategy.MinePlaceStrategy
import tdd.minesweeper.domain.strategy.RandomMinePlaceStrategy

class Cells(
    private val height: Int,
    private val width: Int,
    private val mineCount: Int,
    private val minePlaceStrategy: MinePlaceStrategy = RandomMinePlaceStrategy(),
) {
    private val grid: MutableList<MutableList<Cell>> =
        MutableList(height) { MutableList(width) { Land() } }

    init {
        val minePlaces = minePlaceStrategy.calcMinePlace(height, width, mineCount)
        minePlaces.forEach { (row, col) -> plantMine(row, col) }
        updateAdjacentMineCountOfGrid()
    }

    fun getCell(row: Int, col: Int): Cell {
        return grid[row][col]
    }

    private fun updateAdjacentMineCountOfGrid() {
        grid.forEachIndexed { rowIndex, _ ->
            updateAdjacentMineCountOfRow(rowIndex)
        }
    }

    private fun updateAdjacentMineCountOfRow(rowIndex: Int) {
        val row = grid[rowIndex]
        row.forEachIndexed { colIndex, _ ->
            updateAdjacentMineCountOfCell(rowIndex, colIndex)
        }
    }

    private fun updateAdjacentMineCountOfCell(rowIndex: Int, colIndex: Int) {
        val cell = grid[rowIndex][colIndex]
        if (cell is Mine) {
            return
        }

        val adjacentMineCount = Direction.entries.count { dir ->
            val targetCol = colIndex + dir.dx
            val targetRow = rowIndex + dir.dy

            if (isOutOfBound(targetRow, targetCol)) {
                return@count false
            }

            grid[targetRow][targetCol] is Mine
        }

        (cell as Land).adjacentMineCount = adjacentMineCount
    }

    private fun isOutOfBound(row: Int, col: Int): Boolean {
        return !(row in 0 until height && col in 0 until width)
    }

    private fun plantMine(row: Int, col: Int) {
        grid[row][col] = Mine()
    }

    fun getActiveMineCount(): Int {
        return grid.flatten()
            .filter { !it.isOpened() }
            .count { it is Mine }
    }

    fun openCellAndAdjacentCells(row: Int, col: Int): Boolean {
        val cell = grid[row][col]

        if (cell is Mine) {
            cell.open()
            return false
        }

        if (cell.isOpened()) {
            return true
        }

        bfs(row, col)
        return true
    }

    private fun bfs(row: Int, col: Int) {
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(row to col)

        while (queue.isNotEmpty()) {
            val (currentRow, currentCol) = queue.removeFirst()
            openCell(currentRow, currentCol, queue)
        }
    }

    private fun openCell(row: Int, col: Int, queue: ArrayDeque<Pair<Int, Int>>) {
        val cell = grid[row][col]

        if (cell.isOpened()) {
            return
        }

        cell.open()

        if (cell.noAdjacentMines()) {
            addAdjacentCellToQueue(row, col, queue)
        }
    }

    private fun addAdjacentCellToQueue(
        currentRow: Int,
        currentCol: Int,
        queue: ArrayDeque<Pair<Int, Int>>,
    ) {
        minesweeper.domain.Direction.entries.forEach { dir ->
            val newRow = currentRow + dir.dx
            val newCol = currentCol + dir.dy

            if (isOutOfBound(newRow, newCol)) {
                return@forEach
            }

            if (grid[newRow][newCol].isOpened()) {
                return@forEach
            }

            queue.add(newRow to newCol)
        }
    }

    fun getGrid(): List<List<Cell>> {
        return grid.map { it.toList() }
    }

    fun isClear(): Boolean {
        return grid.flatten()
            .all { it.isOpened() || it is Mine }
    }
}
