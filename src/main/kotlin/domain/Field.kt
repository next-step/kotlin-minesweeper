package domain

class Field(val width: Int, val height: Int) {
    val cells: List<List<Cell>>
    var status: Status

    init {
        validateSize(width)
        validateSize(height)

        cells = (0 until height).map {
            (0 until width).map { Cell() }
        }

        status = Waiting()
    }

    private fun validateSize(len: Int) {
        require(len > 0) { "입력값은 양의 정수여야 합니다." }
    }

    fun setMine(selector: PositionSelector) {
        check(status is Waiting)

        var position = selector.selectPosition()
        while (cellOf(position).isMine) {
            position = selector.selectPosition()
        }

        cellOf(position).isMine = true
    }

    fun setHints() {
        check(status is Waiting)

        recursiveSetHint(Position(0, 0))
        status = status.next()
    }

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

    fun clickCell(x: Int, y: Int) {
        check(status is Running)

        val cell = cellOf(Position(x, y))
        if (cell.isOpened) return

        recursiveOpen(Position(x, y))

        if (cell.isMine) {
            (status as Running).stillAlive = false
            return
        }
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

    private fun checkRunningStatus() {
        if (!(status as Running).stillAlive) {
            status = status.next()
            return
        }

        val opened = cells.flatten().count { it.isOpened }
        val mine = cells.flatten().count { it.isMine }
        val size = width * height

        if (size - opened == mine) {
            (status as Running).stillAlive = true
            status = status.next()
        }
    }

    fun isFinished(): Boolean {
        check(status is Running || status is Finished)

        checkRunningStatus()
        return status is Finished
    }

    fun userWins(): Boolean {
        check(status is Finished)

        return status is FoundAll
    }
}
