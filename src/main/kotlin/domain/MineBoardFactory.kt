package domain

import domain.coordinategenerator.BoardCoordinatesGenerator
import domain.coordinategenerator.CoordinatesGenerator

object MineBoardFactory {

    fun create(
        boardCoordinatesGenerator: BoardCoordinatesGenerator,
        mineCoordinatesGenerator: CoordinatesGenerator
    ): MineBoard {
        val mineBoardCoordinates = boardCoordinatesGenerator.generate()
        val mineCoordinates = mineCoordinatesGenerator.generate()

        val map = mineBoardCoordinates
            .map { Pair(it, mineCoordinates.contains(it)) }
            .map { Pair(it.first, Block.from(it.second)) }
            .toMap()

        return MineBoard(map)
    }
}
