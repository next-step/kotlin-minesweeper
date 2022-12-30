package domain

import domain.strategy.BoardGenerateStrategy
import domain.strategy.RandomMineBoardGenerateStrategy
import dto.FieldWithCoordinate

class Board(
    val height: Height,
    val width: Width,
    mineCnt: MineCnt,
    strategy: BoardGenerateStrategy = RandomMineBoardGenerateStrategy(),
) {
    private val fields: Fields = strategy.generate(height, width, mineCnt)

    fun getField(coordinate: Coordinate): Field {
        return fields.getField(coordinate)
    }

    fun getNearByMineCount(coordinate: Coordinate): Int {
        return fields.getNearByFields(coordinate).count { it.field is Mine }
    }

    fun isMine(coordinate: Coordinate): Boolean {
        return fields.getField(coordinate) is Mine
    }

    fun isGameOver(): Boolean {
        return fields.isLandAllOpened()
    }

    fun open(coordinate: Coordinate) {
        fields.open(coordinate)
        getNotOpenedLandAndHasNoMineWithCoordinate(coordinate).forEach { open(it.coordinate) }
    }

    private fun getNotOpenedLandAndHasNoMineWithCoordinate(coordinate: Coordinate): List<FieldWithCoordinate> {
        if (hasMineNearby(coordinate)) {
            return emptyList()
        }

        return getNotOpenedLand(coordinate)
    }

    private fun hasMineNearby(coordinate: Coordinate): Boolean {
        return fields.getNearByFields(coordinate).any { it.field is Mine }
    }

    private fun getNotOpenedLand(coordinate: Coordinate): List<FieldWithCoordinate> {
        return fields.getNearByFields(coordinate).filter { it.field is Land && it.field.isOpened.not() }
    }
}
