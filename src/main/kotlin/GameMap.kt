class GameMap(field: List<List<Tile>>) {
    private val _field: List<List<Tile>> = field.map { it.toList() }.toList()
    val field: List<List<Tile>> get() = _field.map { it.toList() }.toList()

    init {
        require(field.isNotEmpty()) { "높이 값은 0보다 커야합니다" }
        require(field[0].isNotEmpty()) { "너비 값은 0보다 커야합니다" }
    }

    fun isMine(point: Point): Boolean {
        require(point.y.value in field.indices) { "y값이 잘못되었습니다. 입력값: ${point.y.value}" }
        require(point.x.value in field[0].indices) { "x값이 잘못되었습니다. 입력값: ${point.x.value}" }
        return field[point.y.value][point.x.value] is Mine
    }

    companion object {
        fun createMap(width: Int, height: Int, mineCount: Int): GameMap {
            return MapGenerator.generate(width, height, mineCount)
        }
    }
}
