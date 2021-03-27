package domain

object MineBoardFactory {

    fun create(width: Int, height: Int): MineBoard {
        require(width > 0 && height > 0) { "너비와 높이는 0보다 커야 합니다. width: $width, height: $height" }

        val map = (1..width * height)
            .map { CoordinateFactory.create(maxX = width, maxY = height, nthNumber = it) }
            .map { Pair(it, Block.NOTHING) }
            .toMap()

        return MineBoard(map)
    }
}
