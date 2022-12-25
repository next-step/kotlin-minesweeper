package domain

import domain.strategy.BoardGenerateStrategy
import domain.strategy.RandomMineBoardGenerateStrategy

class Board(
    val height: Height,
    val width: Width,
    val mineCnt: MineCnt,
    strategy: BoardGenerateStrategy = RandomMineBoardGenerateStrategy(),
) {
    val fields: Fields = strategy.generate(height, width, mineCnt)

    fun getField(height: Int, width: Int): Field {
        val coordinate = Coordinate(height, width)
        return fields.getField(coordinate)
    }

    fun getNearByMineCount(height: Int, width: Int): Int {
        val coordinate = Coordinate(height, width)
        return fields.getNearByFields(coordinate).filterIsInstance<Mine>().count()
    }
}
