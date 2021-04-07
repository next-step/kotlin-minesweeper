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
        return cells.all { it.isMine || it.isOpen }
    }

    fun enterCell(location: Location): Boolean {
        val index = location.getConvertIndex(width)
        if (cells[index].isMine) {
            return false
        }

        Position.values().filter { it.isExist(location, width, height) }
            .map { it.getTargetIndex(index, width) }
            .forEach { cells[it].openIfNotMine() }

        cells[index].isOpen = true

        return true
    }

    private fun getLocation(index: Int): Location {
        return Location(index / width, index % width)
    }

    private fun isExistMine(position: Position, index: Int): Boolean {
        val targetIndex = position.getTargetIndex(index, width)
        return position.isExist(getLocation(index), width, height) && cells[targetIndex].isMine
    }
}
