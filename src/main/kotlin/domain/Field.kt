package domain

class Field(width: Int, height: Int) {
    val cells: List<List<Cell>>
    init {
        cells = (0 until height).map { y ->
            (0 until width).map { x ->
                Cell(Position(x, y))
            }
        }
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
