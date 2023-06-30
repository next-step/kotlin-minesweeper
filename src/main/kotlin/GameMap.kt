class GameMap(field: List<List<Tile>>) {
    private val _field: List<MutableList<Tile>> = field.map { it.toMutableList() }.toList()
    val field: List<List<Tile>> get() = _field.map { it.toList() }.toList()

    init {
        require(field.isNotEmpty()) { "높이 값은 0보다 커야합니다" }
        require(field[0].isNotEmpty()) { "너비 값은 0보다 커야합니다" }
    }

    fun isMine(point: Point): Boolean {
        validatePoint(point)
        return _field[point.x.value][point.y.value] is Mine
    }

    fun setMine(point: Point) {
        validatePoint(point)
        _field[point.y.value][point.x.value] = Mine(point)
    }

    private fun validatePoint(point: Point) {
        require(point.y.value in _field.indices) { "y 값이 잘못되었습니다" }
        require(point.x.value in _field[0].indices) { "x 값이 잘못되었습니다" }
    }

    companion object {
        fun createEmptyMap(width: Int, height: Int): GameMap {
            val field = List(height) { y -> List(width) { x -> Tile(Point.from(x, y)) } }
            return GameMap(field)
        }
    }
}
