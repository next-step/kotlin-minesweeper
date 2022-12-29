package minesweeper.domain

class Matrix(val rows: List<MutableList<Field>>) {
    fun width() = rows[0].size

    fun height() = rows.size

    companion object {
        fun of(width: Int, height: Int) = Matrix(createRows(height, width))

        private fun createRows(height: Int, width: Int) = List(height) { createCols(width) }

        private fun createCols(width: Int) = MutableList(width) { Field() }
    }
}
