package minsweeper.domain.generate

import minsweeper.domain.BoardSize
import minsweeper.domain.Coordinate

interface MineGenerator {

    fun generate(
        boardSize: BoardSize,
        mineAmount: Int,
    ): List<Coordinate>

}