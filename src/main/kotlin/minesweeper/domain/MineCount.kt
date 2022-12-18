package minesweeper.domain

data class MineCount(val value: Int, private val maxMineCount: Int) {
    init {
        require(value in MINIMUM_MINE_COUNT..maxMineCount) { "지뢰의 개수는 1 이상 $maxMineCount 이하의 정수여야 합니다." }
    }

    constructor(value: String, maxMineCount: Int) : this(value.toIntOrNull() ?: throw IllegalArgumentException("숫자 이외의 값은 입력할 수 없습니다."), maxMineCount)

    companion object {
        const val MINIMUM_MINE_COUNT = 1
    }
}
