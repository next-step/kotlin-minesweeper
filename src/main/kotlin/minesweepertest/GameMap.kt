package minesweepertest

class GameMap(
    val blocks: List<MutableList<Any>>
) {

    fun setUp() {
        for (y in blocks.indices) {
            for (x in blocks[y].indices) {
                val left = if (x - 1 >= 0) x - 1 else x
            }
        }

        blocks.getOrNull(1)?.getOrNull(1)
    }

    companion object {
        fun of(height: Int, width: Int, mineCount: Int) {
        }
    }
}
