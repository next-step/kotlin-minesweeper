package minesweeper.domain.cell

@JvmInline
value class CoordinateValue(
    val value: Int,
) {
    init {
        require(value >= 0) { "셀 좌표 값은 음수일 수 없습니다." }
    }

    operator fun plus(other: CoordinateValue): CoordinateValue = CoordinateValue(value + other.value)

    operator fun minus(other: CoordinateValue): CoordinateValue = CoordinateValue(value - other.value)
}
