package minesweeper.domain

class MineMap(width: Int, height: Int, mineCount: Int = 0) {

    private val map: List<List<Boolean>>

    init {
        require(width > 0 && height > 0) { "Invalid Mine Map Size" }
        require(mineCount >= 0 && width * height >= mineCount) { "Invalid Mine Count" }

        val array = Array(width * height) { false }
        array.fill(true, 0, mineCount)
        array.shuffle()
        map = array.toList().chunked(height)
    }

    fun map(): List<List<Boolean>> {
        return map
    }
}
