package minesweeper.domain

class GameMap(
    val blockTable: BlockTable
) {

    companion object {
        fun of(height: Int, width: Int, mineCount: Int): GameMap {
            return GameMap(
                BlockTable.of(height, width, mineCount)
                    .apply { setUp() }
            )
        }
    }
}
