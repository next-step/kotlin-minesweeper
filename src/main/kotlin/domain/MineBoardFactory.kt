package domain

import domain.block.BlockFactory
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
            .map { Pair(it.first, BlockFactory.create(it.second)) }
            .toMap()

        return MineBoard(map)
    }
}
