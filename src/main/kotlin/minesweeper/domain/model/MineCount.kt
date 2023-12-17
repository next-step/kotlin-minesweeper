package minesweeper.domain.model

@JvmInline
value class MineCount private constructor(private val value: Int) {

    init {
        require(value >= MIN_MINE_COUNT) {
            "지뢰의 수는 ${MIN_MINE_COUNT}개 이상이여야 한다."
        }
    }

    fun toInt(): Int = value
    companion object {
        private const val MIN_MINE_COUNT = 1
        fun from(mineCount: Int): MineCount = MineCount(mineCount)
    }
}
