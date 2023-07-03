package domain.model

import domain.MapGenerator

class GameMap(field: List<List<Tile>>) {
    private val _field: List<List<Tile>> = field.map { it.toList() }.toList()
    val field: List<List<Tile>> get() = _field.map { it.toList() }.toList()
    val info = MapInfo(field[0].size, field.size)

    init {
        require(field.isNotEmpty()) { "높이 값은 0보다 커야합니다" }
        require(field[0].isNotEmpty()) { "너비 값은 0보다 커야합니다" }
    }

    fun isMine(point: Point): Boolean {
        require(point.y.value in 0 until info.height) { "y값이 잘못되었습니다. 입력값: ${point.y.value}" }
        require(point.x.value in 0 until info.width) { "x값이 잘못되었습니다. 입력값: ${point.x.value}" }
        return field[point.y.value][point.x.value] is Mine
    }

    fun isAllOpened(): Boolean {
        field.flatten().forEach {
            if (it !is Mine && !it.isOpened) return false
        }
        return true
    }

    fun mineCountInSquare(point: Point): Int {
        val y = point.y.value
        var count = 0

        for (i in y - 1..y + 1) {
            if (i < 0 || i >= info.height) continue
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

    fun openTile(point: Point) {
        val y = point.y.value

        for (i in y - 1..y + 1) {
            if (i < 0 || i >= info.height) continue
            val x = point.x.value
            openColumn(x, i)
        }
    }

    private fun mineCountInColumn(x: Int, y: Int): Int {
        var count = 0

        for (i in x - 1..x + 1) {
            if (i < 0 || i >= info.width) continue
            if (field[y][i] is Mine) count++
        }
        return count
    }

    private fun updateNumber(tile: Tile) {
        if (tile !is NumberTile) return
        val value = mineCountInSquare(tile.point)
        tile.updateValue(value)
    }

    private fun openColumn(x: Int, y: Int) {
        for (i in x - 1..x + 1) {
            if (i < 0 || i >= info.width || field[y][i] is Mine) continue
            field[y][i].open()
        }
    }

    companion object {
        fun create(width: Int, height: Int, mineCount: Int, isOpened: Boolean = false): GameMap {
            return MapGenerator.generate(width, height, mineCount, isOpened)
        }
    }
}
