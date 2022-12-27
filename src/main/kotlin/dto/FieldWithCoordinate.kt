package dto

import domain.Coordinate
import domain.Field

data class FieldWithCoordinate(
    val coordinate: Coordinate,
    val field: Field
)
