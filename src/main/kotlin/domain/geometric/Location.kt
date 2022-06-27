package domain.geometric

data class Location(
    val row: LocationValue,
    val column: LocationValue,
) {

    companion object {
        fun nullable(row: Int, column: Int): Location? {
            if (row < 0 || column < 0) return null
            return Location(LocationValue(row), LocationValue(column))
        }
    }
}

@JvmInline
value class LocationValue(val value: Int) {
    init {
        require(value >= 0) { "위치를 표현하는 값은 0 이상이어야 합니다." }
    }

    operator fun compareTo(other: LocationValue): Int {
        return value compareTo other.value
    }
}
