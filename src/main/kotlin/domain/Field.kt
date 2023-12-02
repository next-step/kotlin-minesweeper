package domain

class Field(val width: Int, val height: Int) {
    val cells: List<List<Cell>>
    init {
        validateSize(width)
        validateSize(height)

        cells = (0 until height).map {
            (0 until width).map { Cell() }
        }
    }

    private fun validateSize(len: Int) {
        require(len > 0) { "입력값은 양의 정수여야 합니다." }
    }

    fun setMine(selector: PositionSelector) {
        var position = selector.selectPosition()
        while (cellOf(position).isMine) {
            position = selector.selectPosition()
        }

        cellOf(position).isMine = true
    }

    fun setHints() = recursiveSetHint(Position(0, 0))

    private fun recursiveSetHint(position: Position) {
        val cell = cellOf(position)
        if (cell.hint != null) return

        val aroundPositions = position.getArounds(width = width, height = height)
        cell.hint = aroundPositions.count { cellOf(it).isMine }

        aroundPositions.forEach {
            recursiveSetHint(it)
        }
    }

    private fun cellOf(position: Position): Cell {
        return cells[position.y][position.x]
    }

    fun clickCell(x: Int, y: Int): Boolean {
        val cell = cellOf(Position(x, y))
        if (cell.isOpened) return true

        recursiveOpen(Position(x, y))
        return !cell.isMine
    }

    private fun recursiveOpen(position: Position) {
        val cell = cellOf(position)
        if (cell.isOpened) return

        cell.open()
        if (cell.hint != 0 || cell.isMine) return

        position.getArounds(width = width, height = height).forEach {
            recursiveOpen(it)
        }
    }

    fun isFinished(): Boolean {
        val opened = cells.flatten().count { it.isOpened }
        val mine = cells.flatten().count { it.isMine }
        val size = width * height

        return (size - opened == mine)
    }
}
