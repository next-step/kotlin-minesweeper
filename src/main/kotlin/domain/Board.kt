package domain

import domain.strategy.BoardGenerateStrategy
import domain.strategy.RandomMineBoardGenerateStrategy

class Board(
    val height: Height,
    val width: Width,
    val mineCnt: MineCnt,
    strategy: BoardGenerateStrategy = RandomMineBoardGenerateStrategy(),
) {
    val fields: Fields = strategy.generate(height.value * width.value, mineCnt)

    fun getField(height: Int, width: Int): Field {
        val coordinate = Coordinate(this.height.value * height + width)
        return fields.getField(coordinate)
    }
}
