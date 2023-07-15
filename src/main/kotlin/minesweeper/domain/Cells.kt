package minesweeper.domain

@JvmInline
value class Cells(
    private val values: List<List<Cell>> = emptyList(),
) : List<List<Cell>> by values {
    val height: Int
        get() = values.size

    val width: Int
        get() = values.first().size

    init {
        validateHeightIsPositive()
        validateWidthIsPositive()
        validateSameWidth()
    }

    private fun validateHeightIsPositive() {
        require(height > 0) { "높이는 0보다 커야 합니다." }
    }

    private fun validateWidthIsPositive() {
        require(width > 0) { "너비는 0보다 커야 합니다." }
    }

    private fun validateSameWidth() {
        require(values.all { it.size == width }) { "너비가 일정하지 않습니다." }
    }

    fun allOpened(): Boolean {
        return values.all { row ->
            row.filterIsInstance<Normal>().all { cell -> cell.isOpened }
        }
    }

    fun open(position: Position): Cell {
        return values[position].open()
    }

    operator fun get(position: Position): Cell {
        return values[position]
    }

    operator fun List<List<Cell>>.get(position: Position): Cell {
        return values[position.y][position.x]
    }
}
