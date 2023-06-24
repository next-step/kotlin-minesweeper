package minesweeper.domain.flag

class BlockFlag : Flag() {

    private var aroundMineCount: Int = DEFAULT_MINE_COUNT

    fun updateAroundMineCount(aroundMineCount: Int) {
        check(value = aroundMineCount in ALLOW_AROUND_MINE_RANGE) {
            "블록 주변 지뢰 개수는 $ALLOW_AROUND_MINE_RANGE 범위여야 합니다. 입력값 : $aroundMineCount"
        }

        this.aroundMineCount = aroundMineCount
    }

    override fun currentState(): String = aroundMineCount.toString()

    companion object {
        private const val DEFAULT_MINE_COUNT: Int = 0
        private val ALLOW_AROUND_MINE_RANGE: IntRange = 0 until 9
    }
}
