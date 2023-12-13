package gamemap

class GameMapCreator(
    private val width: Int,
    private val height: Int,
    private val mineCount: Int,
) {
    init {
        require(width > 0) { "invalid game map width" }
        require(height > 0) { "invalid game map height" }
        require(mineCount > 0) { "game map should have at least 1 mine cell" }
        require(width * height > mineCount) { "mine count cannot be larger than game map size" }
    }
    fun create(): GameMap {

        val minePositions = mutableSetOf<MinePosition>()

        while (minePositions.size != mineCount) {
            minePositions.add(randomPosition(width, height))
        }

        val initialGameMapScaffold = List(height) { row ->
            List(width) { col ->
                Cell(
                    isMine = MinePosition(row = row, col = col) in minePositions,
                    adjacentMineCount = minePositions.count { it.isAdjacentTo(cellRowIdx = row, cellColIdx = col) }
                )
            }
        }

        return GameMap(initialGameMapScaffold)
    }

    private fun randomPosition(width: Int, height: Int): MinePosition =
        MinePosition(row = (0 until height).random(), col = (0 until width).random())

    private data class MinePosition(
        val row: Int,
        val col: Int,
    ) {
        fun isAdjacentTo(cellRowIdx: Int, cellColIdx: Int): Boolean {
            return row in cellRowIdx - 1..cellRowIdx + 1 && col in cellColIdx - 1..cellColIdx + 1 && !isAtPosition(cellRowIdx, cellColIdx)
        }

        private fun isAtPosition(cellRowIdx: Int, cellColIdx: Int) = row == cellRowIdx && col == cellColIdx
    }
}
