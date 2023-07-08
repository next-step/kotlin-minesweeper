package domain

data class Row(val spaces: List<Space>) {
    fun chunked(size: Int): List<Row> {
        return spaces.chunked(size).map { Row(it) }
    }

    companion object {
        fun emptyRow(size: Int): Row {
            return Row(
                MutableList(size) { Space.Empty }
            )
        }
    }
}
