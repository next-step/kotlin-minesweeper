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
        val coordinate = getCoordinateByHeightAndWidth(height, width)
        return fields.getField(coordinate)
    }

    fun getNearByMineCount(height: Int, width: Int): Int {
        val coordinate = getCoordinateByHeightAndWidth(height, width)
        return fields.getNearByFields(coordinate).count { it.field is Mine }
    }

    fun isMine(height: Int, width: Int): Boolean {
        val coordinate = getCoordinateByHeightAndWidth(height, width)
        return fields.getField(coordinate) is Mine
    }

    fun isGameOver(): Boolean {
        return fields.isLandAllOpened()
    }

    fun open(height: Int, width: Int) {
        val coordinate = getCoordinateByHeightAndWidth(height, width)
        fields.open(coordinate)

        getNotOpenedLandAndHasNoMineWithCoordinate(coordinate).forEach { land ->
            open(land.coordinate.row, land.coordinate.col)
        }
    }

    private fun getCoordinateByHeightAndWidth(height: Int, width: Int): Coordinate {
        return Coordinate(height, width).apply { validateCoordinate(this) }
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

    private fun validateCoordinate(coordinate: Coordinate) {
        require(coordinate.row in COORDINATE_MIN_VALUE until height.value) { INVALID_HEIGHT }
        require(coordinate.col in COORDINATE_MIN_VALUE until width.value) { INVALID_WIDTH }
    }

    companion object {
        private const val INVALID_HEIGHT = "올바르지 않은 높이를 열기 위해 시도하고 있어요"
        private const val INVALID_WIDTH = "올바르지 않은 너비를 열기 위해 시도하고 있어요"
        private const val COORDINATE_MIN_VALUE = 0
    }
}
