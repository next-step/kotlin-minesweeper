package domain

data class Position(
    val row: Int,
    val column: Int,
) {
    init {
        require(row >= 0) { ROW_INDEX_EXCEPTION_MESSAGE }
        require(column >= 0) { COL_INDEX_EXCEPTION_MESSAGE }
    }

    companion object {
        private const val ROW_INDEX_EXCEPTION_MESSAGE = "row는 음수일 수 없습니다."
        private const val COL_INDEX_EXCEPTION_MESSAGE = "column은 음수일 수 없습니다."
    }
}
