package domain

sealed class Field(
    val coordinate: Coordinate
) {
    abstract val type: FieldType
}

class Land(
    coordinate: Coordinate,
    override val type: FieldType
) : Field(coordinate) {
    override fun toString(): String {
        return type.description
    }
}

class Mine(
    coordinate: Coordinate,
    override val type: FieldType
) : Field(coordinate) {
    override fun toString(): String {
        return type.description
    }
}
