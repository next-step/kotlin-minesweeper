package domain

import java.lang.IllegalArgumentException

class Fields(
    private val fields: Map<Coordinate, Field>
) {
    fun getField(coordinate: Coordinate): Field {
        return fields[coordinate] ?: throw IllegalArgumentException(NOT_EXIST_FIELD)
    }

    fun getNearByFields(coordinate: Coordinate): List<Field> {
        val nearByCoordinates = coordinate.getNearByCoordinates()
        return nearByCoordinates.mapNotNull { fields[it] }
    }

    companion object {
        private const val NOT_EXIST_FIELD = "해당 좌표에는 필드가 존재하지 않아요."
    }
}
