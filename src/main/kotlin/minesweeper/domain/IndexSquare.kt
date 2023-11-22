package minesweeper.domain

enum class IndexSquare(val position: Position) {
    LEFT_TOP(Position(-1, 1)),
    TOP(Position(0, 1)),
    RIGHT_TOP(Position(1, 1)),
    LEFT(Position(-1, 0)),
    RIGHT(Position(1, 0)),
    LEFT_BOTTOM(Position(-1, -1)),
    BOTTOM(Position(0, -1)),
    RIGHT_BOTTOM(Position(1, -1));

    companion object {
        fun squareIndex(
            mineSweeperIndex: MineSweeperIndex,
            mineSweeperIndexes: MineSweeperMap
        ): List<MineSweeperIndex> {
            val squares = filterSquare(mineSweeperIndex, mineSweeperIndexes).flatMap { indexSquare ->
                mineSweeperIndexes.filter {
                    it.position == mineSweeperIndex.position + indexSquare.position
                }
            }
            return squares
        }

        private fun filterSquare(
            mineSweeperIndex: MineSweeperIndex,
            mineSweeperIndexes: MineSweeperMap
        ): List<IndexSquare> {
            return values().filter { indexSquare ->
                mineSweeperIndex.position + indexSquare.position in mineSweeperIndexes.map { it.position }
            }
        }
    }
}
