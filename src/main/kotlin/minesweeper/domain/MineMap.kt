package minesweeper.domain

class MineMap private constructor(private val map: List<List<Boolean>>) {

    fun map(): List<List<Boolean>> {
        return map
    }

    companion object Factory {
        fun build(width: Int, height: Int): MineMap {
            val array = Array(width * height) { false }
            return MineMap(array.toList().chunked(height))
        }
    }
}