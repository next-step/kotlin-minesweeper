package minesweeper.domain

class MineMap(width: Int, height: Int, mineCount: Int = 0) {

    private val map: List<List<Cell>>

    init {
        require(width > 0 && height > 0) { "Invalid Mine Map Size" }
        require(mineCount >= 0 && width * height >= mineCount) { "Invalid Mine Count" }
        map = Array(width * height) { Cell() }
            .apply { fill(MineCell(), 0, mineCount) }
            .apply { shuffle() }
            .toList()
            .chunked(height)
    }

    fun map(): List<List<Cell>> {
        return map
    }
}
