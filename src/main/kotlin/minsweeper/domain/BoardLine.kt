package minsweeper.domain

data class BoardLine(val cells: List<Cell>) {

    fun find(column: Int): Cell {
        require(column < cells.size) { COLUMN_LESS_THAN_CELLS_SIZE }
        return cells[column]
    }

    companion object {
        private const val COLUMN_LESS_THAN_CELLS_SIZE = "가지고 있는 셀의 크기를 초과해 찾을 수 없습니다"
    }

}
