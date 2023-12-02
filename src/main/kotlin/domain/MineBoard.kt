package domain

data class MineBoard(
    val rows: List<MineBoardRow>
) {
    companion object {
        fun of(mineConfig: MineConfig): MineBoard {
            val totalTileCount = mineConfig.height * mineConfig.width
            val minePositions = (1 .. totalTileCount).shuffled().slice(0 until mineConfig.mineCount)

            val rows = List(mineConfig.height) { column ->
                createRow(column, mineConfig.width, minePositions)
            }

            return MineBoard(rows)
        }

        private fun createRow(column: Int, row: Int, minePositions: List<Int>): MineBoardRow {
            val tiles = List(row) {
                val currentPosition = column * row + it

                Tile.of(minePositions.contains(currentPosition))
            }

            return MineBoardRow(Tiles(tiles))
        }
    }
}
