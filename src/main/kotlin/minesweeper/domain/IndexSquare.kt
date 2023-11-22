package minesweeper.domain

import minesweeper.tdddomain.MineSweeperIndex2

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
        fun squareIndex(position: Position, mineSweeperIndexes: MineSweeperIndexes): MineSweeperIndexes {
            val squares = filterSquare(position, mineSweeperIndexes).map {
                MineSweeperIndex(position + it.position)
            }
            return MineSweeperIndexes(squares)
        }

        fun squareIndex(
            mineSweeperIndex2: MineSweeperIndex2,
            mineSweeperIndexes: List<MineSweeperIndex2>
        ): List<MineSweeperIndex2> {
            val squares = filterSquare(mineSweeperIndex2, mineSweeperIndexes).flatMap { indexSquare ->
                mineSweeperIndexes.filter {
                    it.position == mineSweeperIndex2.position + indexSquare.position
                }
            }
            return squares
        }

        private fun filterSquare(position: Position, mineSweeperIndexes: MineSweeperIndexes): List<IndexSquare> {
            return values().filter { indexSquare ->
                position + indexSquare.position in mineSweeperIndexes.mineSweeperIndexes.map { it.position }
            }
        }

        private fun filterSquare(
            mineSweeperIndex2: MineSweeperIndex2,
            mineSweeperIndexes: List<MineSweeperIndex2>
        ): List<IndexSquare> {
            return values().filter { indexSquare ->
                mineSweeperIndex2.position + indexSquare.position in mineSweeperIndexes.map { it.position }
            }
        }
    }
}
