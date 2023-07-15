package minesweeper.domain

@JvmInline
value class Cells(val values: List<Cell>) : List<Cell> by values {
    fun hasSize(size: Int): Boolean {
        return values.size == size
    }

    fun allOpened(): Boolean {
        return values.filterIsInstance<Normal>().all { it.isOpened }
    }
}
