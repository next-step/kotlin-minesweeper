package minesweeper.domain

@JvmInline
value class NearMineCount(
    val value: Int
) {
    init {
        check(value < 9) { "좌표상 포지션 근처의 지뢰는 9개 미만이어야 합니다." }
    }
}
