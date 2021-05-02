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

        return MineBoard(
            width = boardCoordinatesGenerator.width,
            height = boardCoordinatesGenerator.height,
            value = mineBoardCoordinates.associateWith { BlockFactory.create(mineCoordinates.contains(it)) }
        )
    }
}
