package domain

import domain.coordinategenerator.CoordinatesGenerator

object MineBoardFactory {

    fun create(width: Int, height: Int, mineCoordinatesGenerator: CoordinatesGenerator): MineBoard {
        require(width > 0 && height > 0) { "너비와 높이는 0보다 커야 합니다. width: $width, height: $height" }

        val mineCoordinates = mineCoordinatesGenerator.generate()

        val map = (1..width * height)
            .map { CoordinateFactory.create(maxX = width, maxY = height, nthNumber = it) }
            .map { Pair(it, mineCoordinates.contains(it)) }
            .map { Pair(it.first, Block.from(it.second)) }
            .toMap()

        return MineBoard(map)
    }
}
