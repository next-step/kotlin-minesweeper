package minesweeper.domain

private const val MIN_MINE_NUMBER = 1

@JvmInline
value class CountOfMine(
    val value: Int,
) {
    init {
        require(value >= MIN_MINE_NUMBER) { "지뢰 갯수는 최소 1개 이상이어야한다." }
    }
}
