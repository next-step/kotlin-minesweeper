package minesweeper.domain

import minesweeper.domain.Mine.Companion.MINE_SYMBOL

class MineMap(
    private val dimension: MapDimension,
    mines: Mines
) {
    private val mineMap: List<List<String>>
        get() = mines.setIntoMap(initMap(dimension.height, dimension.width))

    var mines = mines
        private set

    init {
        validateMineCount()
    }

    constructor(height: Int, width: Int, mineCount: Int) : this(
        dimension = MapDimension(height, width),
        mines = Mines((1..mineCount).map { Mine(MapDimension(height, width)) })
    )

    private fun validateMineCount() {
        while (mines.hasDuplicate()) {
            val nonDuplicateMines = mines.duplicateRemoved()

            val newMines = (1..mines.duplicateSize()).map { Mine(dimension) }
            mines = Mines(nonDuplicateMines + newMines)
        }
    }

    fun getMineCountedMap(): List<List<String>> {
        var map = this.mineMap
        val height = dimension.height.value
        val width = dimension.width.value

        repeat(height) { x ->
            repeat(width) { y ->
                map = mineCountedMap(x, y, map)
            }
        }

        return map
    }

    private fun mineCountedMap(x: Int, y: Int, mineMap: List<List<String>>): List<List<String>> {
        val map = mineMap.map { it.toMutableList() }.toMutableList()
        var count = 0

        repeat(DIRECTION_X.size) { index ->
            val (nextX: Int, nextY: Int) = setNextXY(x, y, index)

            if (isInBounds(nextX, nextY)) {
                if (map[nextX][nextY] == MINE_SYMBOL) count++
            }
        }

        if (isInBounds(x, y)) {
            if (map[x][y] == MAP_SYMBOL)
                map[x][y] = count.toString()
        }
        return map
    }

    private fun setNextXY(x: Int, y: Int, i: Int): Pair<Int, Int> {
        val nextX = x + DIRECTION_X[i]
        val nextY = y + DIRECTION_Y[i]
        return Pair(nextX, nextY)
    }

    private fun isInBounds(x: Int, y: Int): Boolean {
        if (x in 0 until dimension.height.value &&
            y in 0 until dimension.width.value
        ) {
            return true
        }
        return false
    }

    companion object {
        private const val MAP_SYMBOL = "C"

        private val DIRECTION_X = listOf(-1, -1, -1, 0, 0, 1, 1, 1)
        private val DIRECTION_Y = listOf(-1, 0, 1, -1, 1, -1, 0, 1)

        fun initMap(height: Height, width: Width): List<List<String>> {
            return List(height.value) { List(width.value) { MAP_SYMBOL } }
        }
    }
}
