package minesweeper.domain

class MineMap(
    private val height: Int,
    private val width: Int,
    private val mineCount: Int
) {

    fun generate(): List<Cell> {
        val randomIndexes = List(height * width) { it }.shuffled().take(mineCount)

        return List(height * width) { index ->
            if (index in randomIndexes) {
                return@List Cell(index, true)
            }
            return@List Cell(index, false)
        }
    }
}
