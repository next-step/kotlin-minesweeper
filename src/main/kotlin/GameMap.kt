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

    fun mineCountInSquare(point: Point): Int {
        val height = field.size
        val y = point.y.value
        var count = 0

        for (i in y - 1..y + 1) {
            if (i < 0 || i >= height) continue
            val x = point.x.value
            count += mineCountInColumn(x, i)
        }
        return count
    }

    fun updateField() {
        field.flatten().forEach {
            updateNumber(it)
        }
    }

    private fun mineCountInColumn(x: Int, y: Int): Int {
        val width = field[0].size
        var count = 0

        for (i in x - 1..x + 1) {
            if (i < 0 || i >= width) continue
            if (field[y][i] is Mine) count++
        }
        return count
    }

    private fun updateNumber(tile: Tile) {
        if (tile !is NumberTile) return
        val value = mineCountInSquare(tile.point)
        tile.updateValue(value)
    }

    companion object {
        fun create(width: Int, height: Int, mineCount: Int, isOpened: Boolean = false): GameMap {
            return MapGenerator.generate(width, height, mineCount, isOpened)
        }
    }
}
