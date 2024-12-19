package minsweeper.domain

data class BoardLines(val lines: List<BoardLine>) {

    fun find(row: Int): BoardLine {
        require(row < lines.size) { COLUMN_LESS_THAN_LINES_SIZE }
        return lines[row]
    }

    companion object {
        private const val COLUMN_LESS_THAN_LINES_SIZE = "가지고 있는 라인의 크기를 초과해 찾을 수 없습니다"
    }

}
