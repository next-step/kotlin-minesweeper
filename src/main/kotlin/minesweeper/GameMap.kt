package minesweeper

class GameMap(
    val blockTable: BlockTable
) {

    companion object {
        fun of(height: Int, width: Int, mineCount: Int): GameMap {
            return GameMap(
                BlockTable(
                    mapOf(
                        (0 to 0) to CleanBlock(),
                        (0 to 1) to CleanBlock(),
                        (0 to 2) to CleanBlock(),
                        (1 to 0) to CleanBlock(),
                        (1 to 1) to CleanBlock(),
                        (1 to 2) to CleanBlock(),
                        (2 to 0) to CleanBlock(),
                        (2 to 1) to MineBlock(),
                        (2 to 2) to MineBlock(),
                    )
                )
            )
        }
    }
}
