package model

class Map(val width: Int, val height: Int) {
    private val _cells: MutableList<Cell> = mutableListOf()
    val cells: List<Cell> = _cells.toList()
    val mines: List<Cell> = _cells.filter { it.isMine }

    fun createDefaultMap(width: Int, height: Int) {
        (0 until width).flatMap { x ->
            (0 until height).map { y ->
                _cells.add(Cell(false, Position(x, y)))
            }
        }
    }

    fun createRandomMines(mineCount: Int) {
        _cells.shuffled().take(mineCount).forEach {
            notMineToMine(it)
        }
    }

    fun calculateMineAroundCount(maxX: Int, maxY: Int) {
        _cells.forEach {
            if (it.isMine) {
                getAroundPositions(it.position, maxX, maxY)
            }
        }
    }

    private fun a() {

    }

    private fun getAroundPositions(position: Position, maxX: Int, maxY: Int): List<Position> {
        //if (checkInvalidRange(targetPosition, maxX, maxY)) {
        //    getNotMineCell(targetPosition)?.addCount()
        //}
        val operand = listOf(-1, 0, 1)
        return operand.flatMap { operandX ->
            operand.map { operandY ->
                Position(position.x + operandX, position.y + operandY)
            }
        }.toList()
    }

    private fun checkInvalidRange(targetPosition: Position, maxX: Int, maxY: Int): Boolean {
        return targetPosition.x in 0 until maxX && targetPosition.y in 0 until maxY
    }

    private fun getNotMineCell(targetPosition: Position): Cell? {
        return _cells.firstOrNull { it.match(Cell(false, targetPosition)) && !it.isMine }
    }

    private fun notMineToMine(targetCell: Cell) {
        if (_cells.removeIf { it.match(targetCell) }) {
            _cells.add(Cell(true, targetCell.position))
        }
    }

    override fun toString(): String {
        return _cells.sortedWith(compareBy({ it.position.x }, { it.position.y }))
                .groupBy { it.position.y }.values.joinToString("\n") { it.joinToString(" ") }
    }
}
