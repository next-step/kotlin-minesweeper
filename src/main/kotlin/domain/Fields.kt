package domain

import dto.FieldWithCoordinate

class Fields(
    private val fields: Map<Coordinate, Field>,
) {
    fun getField(coordinate: Coordinate): Field {
        return fields[coordinate] ?: throw IllegalArgumentException(NOT_EXIST_FIELD)
    }

    fun getNearByFields(coordinate: Coordinate): List<FieldWithCoordinate> {
        val nearByCoordinates = coordinate.getNearByCoordinates()
        return nearByCoordinates.mapNotNull { nearByCoordinate ->
            fields[nearByCoordinate]?.let { field -> FieldWithCoordinate(nearByCoordinate, field) }
        }
    }

    fun isLandAllOpened(): Boolean {
        return fields.values.filterIsInstance<Land>()
            .all { it.isOpened }
    }

    fun open(coordinate: Coordinate) {
        when (val field = this.getField(coordinate)) {
            is Land -> field.open()
            is Mine -> throw IllegalStateException(CANNOT_OPEN_MINE)
        }
    }

    companion object {
        private const val NOT_EXIST_FIELD = "해당 좌표에는 필드가 존재하지 않아요."
        private const val CANNOT_OPEN_MINE = "지뢰 좌표는 열 수 없어요."
    }
}
