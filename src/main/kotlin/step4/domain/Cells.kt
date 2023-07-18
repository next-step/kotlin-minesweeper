package step4.domain

@JvmInline
value class Cells(
    val values: Map<Coordinate, Cell>,
) {
    fun installMine(mineCount: Int) {
        require(mineCount >= 1) { "지뢰 갯수는 1개 이상이어야 합니다." }
    }
}
