package minesweeper.domain

class MineMap private constructor(private val map: List<List<Boolean>>) {

    fun map(): List<List<Boolean>> {
        return map
    }

    companion object Factory {
        fun build(width: Int, height: Int, mineCount: Int = 0): MineMap {
            require(width > 0 && height > 0) { "Invalid Mine Map Size" }
            require(mineCount >= 0 && width * height >= mineCount) { "Invalid Mine Count" }
            val array = Array(width * height) { false }
            array.fill(true, 0, mineCount)
            array.shuffle()
            return MineMap(array.toList().chunked(height))
        }
    }
}
