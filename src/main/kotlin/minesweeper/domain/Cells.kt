package minesweeper.domain

class Cells(val cells: List<Cell>, val width: Int, val height: Int) {

    init {
        require(width > 0 && height > 0) { "너비와 높이는 0보다 커야합니다" }
        require(cells.size == width * height) { "셀의 크기는 너비와 높이를 곱한 값이어야 합니다" }
    }

    fun getCellValue(index: Int): Int {
        return Position.getIndexesNearByLocation(getLocation(index), width, height).count { cells[it].isMine }
    }

    fun isAllOpen(): Boolean {
        return cells.all { it.isMine || it.isOpen }
    }

    fun enterCell(location: Location): Boolean {
        val index = location.getConvertIndex(width)
        if (cells[index].isMine) {
            return false
        }

        Position.getIndexesNearByLocation(location, width, height)
            .forEach { cells[it].openIfNotMine() }

        cells[index].openIfNotMine()

        return true
    }

    private fun getLocation(index: Int): Location {
        return Location(index / width, index % width)
    }
}
