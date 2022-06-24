package domain.geometric

data class Location(
    val row: LocationValue,
    val column: LocationValue,
) {
    fun isLocatedOutside(other: Location): Boolean {
        return row >= other.row && column >= other.column
    }

    fun isLocatedInside(other: Location): Boolean {
        return row <= other.row && column <= other.column
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
