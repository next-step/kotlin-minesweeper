package step4.domain

@JvmInline
value class Cells(
    val values: Map<Coordinate, Cell>,
) {
    fun installMine(mineCount: Int) {
        require(mineCount >= MINIMUM_MINE_COUNT) { "지뢰 갯수는 ${MINIMUM_MINE_COUNT}개 이상이어야 합니다." }
        require(mineCount < values.size) { "보유한 cell보다 많은 지뢰를 설치할 수 없습니다." }
    }

    fun open(coordinate: Coordinate) {
        val cell = values[coordinate] ?: throw IllegalArgumentException("존재하지 않는 좌표는 입력될 수 없습니다.")
        cell.open()
    }

    companion object {
        private const val MINIMUM_MINE_COUNT = 1
    }
}
