import domain.Point

class AroundMineSetting(board: Map<Point, Square>) {

    private val _board: MutableMap<Point, Square> = board.toMutableMap()

    init {
        update()
    }

    private fun update() {
        _board.keys.forEach {
            updateMine(it)
        }
    }

    private fun updateMine(currentPoint: Point) {
        val square = _board.getValue(currentPoint)
        if (!square.isMine()) {
            val countMine = AroundMine(_board, currentPoint).countMine()
            _board.replace(currentPoint, NonMine(countMine))
        }
    }

    val board: Map<Point, Square>
        get() = _board.toMap()
}
