package gamemap

class GameMapCreator(
    private val mapSizeParams: MapSizeParams,
    private val mineCount: Int,
) {
    init {
        require(mineCount > 0) { "game map should have at least 1 mine cell" }
        require(mapSizeParams.size > mineCount) { "mine count cannot be greater than or equal to game map size" }
    }

    fun create(): GameMap {
        val minePositions = MinePositions.ofSize(mineCount) {
            MinePosition.ofRandom(mapSizeParams.width, mapSizeParams.height)
        }

        val initialGameMapScaffold = List(mapSizeParams.height) { row ->
            List(mapSizeParams.width) { col ->
                Cell(
                    isMine = MinePosition(row = row, col = col) in minePositions,
                    adjacentMineCount = minePositions.countAdjacentTo(cellRowIdx = row, cellColIdx = col)
                )
            }
        }

        return GameMap(initialGameMapScaffold)
    }

    data class MapSizeParams(
        val width: Int,
        val height: Int,
    ) {
        init {
            require(width > 0) { "invalid game map width" }
            require(height > 0) { "invalid game map height" }
        }

        val size = width * height
    }

    private data class MinePosition(
        val row: Int,
        val col: Int,
    ) {
        fun isAdjacentTo(cellRowIdx: Int, cellColIdx: Int): Boolean {
            return (row in cellRowIdx - 1..cellRowIdx + 1) &&
                (col in cellColIdx - 1..cellColIdx + 1) &&
                !isAtPosition(cellRowIdx, cellColIdx)
        }

        private fun isAtPosition(cellRowIdx: Int, cellColIdx: Int) = row == cellRowIdx && col == cellColIdx

        companion object {
            fun ofRandom(width: Int, height: Int): MinePosition =
                MinePosition(row = (0 until height).random(), col = (0 until width).random())
        }
    }

    private class MinePositions(
        private val mineSet: MutableSet<MinePosition>
    ) {
        fun countAdjacentTo(cellRowIdx: Int, cellColIdx: Int) = mineSet.count {
            it.isAdjacentTo(cellRowIdx = cellRowIdx, cellColIdx = cellColIdx)
        }

        operator fun contains(minePosition: MinePosition): Boolean = mineSet.contains(minePosition)

        companion object {
            fun ofSize(size: Int, creator: () -> MinePosition): MinePositions {
                val minePositions = mutableSetOf<MinePosition>()

                while (minePositions.size != size) {
                    minePositions.add(creator())
                }

                return MinePositions(minePositions)
            }
        }
    }
}
