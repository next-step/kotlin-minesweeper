package domain.geometric

data class Location(
    val row: LocationValue,
    val column: LocationValue,
)

@JvmInline
value class LocationValue(val value: Int) {
    init {
        require(value >= 0) { "위치를 표현하는 값은 0 이상이어야 합니다." }
    }
}
