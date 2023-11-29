package domain

class Field(width: Int, height: Int) {
    val cells: List<List<Cell>>
    init {
        validateSize(width)
        validateSize(height)

        cells = (0 until height).map { y ->
            (0 until width).map { x ->
                Cell(Position(x, y))
            }
        }
    }

    private fun validateSize(len: Int) {
        require(len > 0) { "입력값은 양의 정수여야 합니다." }
    }

    fun setMine(selector: PositionSelector) {
        var position = selector.selectPosition()
        while (isMine(position)) {
            position = selector.selectPosition()
        }

        cellOf(position).isMine = true
    }

    private fun cellOf(position: Position): Cell {
        return cells[position.x][position.y]
    }

    fun isMine(position: Position): Boolean {
        return cellOf(position).isMine
    }
}
