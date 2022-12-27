package domain

import domain.strategy.BoardGenerateStrategy
import domain.strategy.RandomMineBoardGenerateStrategy
import dto.FieldWithCoordinate

class Board(
    val height: Height,
    val width: Width,
    val mineCnt: MineCnt,
    strategy: BoardGenerateStrategy = RandomMineBoardGenerateStrategy(),
) {
    private val fields: Fields = strategy.generate(height, width, mineCnt)

    fun getField(height: Int, width: Int): Field {
        val coordinate = Coordinate(height, width)
        return fields.getField(coordinate)
    }

    fun getNearByMineCount(height: Int, width: Int): Int {
        val coordinate = Coordinate(height, width)
        return fields.getNearByFields(coordinate).count { it.field is Mine }
    }

    fun isMine(height: Int, width: Int): Boolean {
        val coordinate = Coordinate(height, width)
        return fields.getField(coordinate) is Mine
    }

    fun isGameOver(): Boolean {
        return fields.isLandAllOpened()
    }

    tailrec fun open(height: Int, width: Int) {
        // 해당 좌표 열기
        val coordinate = Coordinate(height, width)
        fields.open(coordinate)

        // 인근에 지뢰가 아닌 좌표 열기
        getNotOpenedLandAndHasNoMineWithCoordinate(coordinate).forEach { land ->
            open(land.coordinate.row, land.coordinate.col)
        }
    }

    private fun getNotOpenedLandAndHasNoMineWithCoordinate(coordinate: Coordinate): List<FieldWithCoordinate> {
        if (hasMineNearby(coordinate))
            return emptyList()
        return getNotOpenedLand(coordinate)
    }

    private fun hasMineNearby(coordinate: Coordinate): Boolean {
        return fields.getNearByFields(coordinate).count { it.field is Mine } > 0
    }

    private fun getNotOpenedLand(coordinate: Coordinate): List<FieldWithCoordinate> {
        return fields.getNearByFields(coordinate).filter { it.field is Land && it.field.isOpened.not() }
    }
}
