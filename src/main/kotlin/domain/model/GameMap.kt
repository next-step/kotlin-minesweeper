package domain.model

import domain.BfsAlgorithm
import domain.MapGenerator
import domain.MinesweeperAlgorithm

class GameMap(field: List<List<Tile>>) {
    private val _field: List<List<Tile>> = field.map { it.toList() }.toList()
    val field: List<List<Tile>> get() = _field.map { it.toList() }.toList()
    val info = MapInfo(field[0].size, field.size)

    fun isMine(point: Point): Boolean {
        require(point.y.value in 0 until info.height) { "y값이 잘못되었습니다. 입력값: ${point.y.value}" }
        require(point.x.value in 0 until info.width) { "x값이 잘못되었습니다. 입력값: ${point.x.value}" }
        return field[point.y.value][point.x.value] is Mine
    }

    fun isAllOpened(): Boolean {
        return field.flatten().filterIsInstance<NumberTile>().all { it.isOpened }
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
        field.flatten().filterIsInstance<NumberTile>().forEach { tile ->
            tile.updateValue(mineCountInSquare(tile.point))
        }
    }

    fun openTile(point: Point, algorithm: MinesweeperAlgorithm = BfsAlgorithm()) {
        algorithm.openTiles(this, point)
    }

    fun isUnopenedNumberTile(x: Int, y: Int): Boolean {
        return field[y][x] is NumberTile && !field[y][x].isOpened
    }

    fun inRange(x: Int, y: Int): Boolean {
        return x in 0 until info.width && y in 0 until info.height
    }

    private fun mineCountInColumn(x: Int, y: Int): Int {
        var count = 0

        for (i in x - 1..x + 1) {
            if (i < 0 || i >= info.width) continue
            if (field[y][i] is Mine) count++
        }
        return count
    }

    companion object {
        fun create(width: Int, height: Int, mineCount: Int, isOpened: Boolean = false): GameMap {
            return MapGenerator.generate(width, height, mineCount, isOpened)
        }
    }
}
