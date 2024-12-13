package minesweeper.domain

class BoardOpener(
    private val cells: Cells,
) {

    fun open(row: Int, col: Int): OpenResult {
        val targetCell = cells.getCell(row, col)

        if (targetCell.isOpened) {
            return OpenResult.CONTINUE
        }

        if (targetCell is Mine) {
            targetCell.open()
            return OpenResult.LOSE
        }

        openAdjacentCells(row, col)
        return OpenResult.CONTINUE
    }

    private fun openAdjacentCells(row: Int, col: Int) {
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(row to col)

        while (queue.isNotEmpty()) {
            val (currentRow, currentCol) = queue.removeFirst()
            openCell(currentRow, currentCol, queue)
        }
    }

    private fun openCell(currentRow: Int, currentCol: Int, queue: ArrayDeque<Pair<Int, Int>>) {
        val cell = cells.getCell(currentRow, currentCol)

        if (cell.isOpened) {
            return
        }

        cell.open()

        if (cell.noAdjacentMines()) {
            addAdjacentCellToQueue(currentRow, currentCol, queue)
        }
    }

    private fun addAdjacentCellToQueue(
        currentRow: Int,
        currentCol: Int,
        queue: ArrayDeque<Pair<Int, Int>>,
    ) {
        Direction.entries.forEach { dir ->
            val newRow = currentRow + dir.dx
            val newCol = currentCol + dir.dy

            if (cells.isOutOfBound(newRow, newCol)) {
                return@forEach
            }

            queue.add(newRow to newCol)
        }
    }
}
