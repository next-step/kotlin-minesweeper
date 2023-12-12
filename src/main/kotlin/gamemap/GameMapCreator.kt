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

        val initialCellParameters = MutableList(height) {
            MutableList(width) {
                Pair(false, 0)
            }
        }

        while (initialCellParameters.flatten().count { it.first } != mineCount) {
            val randomPosition = randomPosition(width, height)
            val row = randomPosition.second
            val col = randomPosition.first
            val cell = initialCellParameters[row][col]

            initialCellParameters[row][col] = cell.copy(first = true)
        }

        val initialGameMapScaffold = List(height) { row ->
            List(width) { col ->
                val isMine = initialCellParameters[row][col].first
                val adjacentMineCount = initialCellParameters
                    .filterIndexed { rowIdx, _ ->
                        rowIdx in Integer.max(row - 1, 0)..Integer.min(
                            row + 1,
                            height - 1
                        )
                    }
                    .map { it.slice(Integer.max(col - 1, 0)..Integer.min(col + 1, width - 1)) }
                    .flatten()
                    .count { it.first }
                    .minus(if (isMine) 1 else 0)
                Cell(
                    isMine = isMine,
                    adjacentMineCount = adjacentMineCount
                )
            }
        }

        return GameMap(initialGameMapScaffold)
    }

    private fun randomPosition(width: Int, height: Int): Pair<Int, Int> =
        Pair((0 until width).random(), (0 until height).random())
}
