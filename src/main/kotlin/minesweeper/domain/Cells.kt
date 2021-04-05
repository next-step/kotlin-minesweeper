package minesweeper.domain

class Cells(val cells: List<Cell>, val width: Int, val height: Int) {

    init {
        require(width > 0 && height > 0) { "너비와 높이는 0보다 커야합니다" }
        require(cells.size == width * height) { "셀의 크기는 너비와 높이를 곱한 값이어야 합니다" }
    }

    fun getCellValue(index: Int): Int {
        return Position.values().filter { isExistMine(it, index) }.count()
    }

    fun isAllOpen(): Boolean {
        return cells.none { !it.isMine && !it.isOpen }
    }

    fun enterCell(index: Int): Boolean {
        if (cells[index].isMine) {
            return false
        }
        val row: Int = index / width
        val column: Int = index % width

        Position.values().filter { it.isExist(row, column, width, height) }
            .map { it.getTargetIndex(index, width) }
            .filter { !cells[it].isMine }
            .forEach { cells[it].isOpen = true }

        cells[index].isOpen = true

        return true
    }

    private fun isExistMine(position: Position, index: Int): Boolean {
        val row: Int = index / width
        val column: Int = index % width
        val targetIndex = position.getTargetIndex(index, width)
        return position.isExist(row, column, width, height) && cells[targetIndex].isMine
    }
}
