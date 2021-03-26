package minesweeper.inputdata

class MineGameCondition(
    height: PositiveNumber,
    width: PositiveNumber,
    mineCount: PositiveNumber
) {
    init {
        require(height * width > mineCount) { "지뢰 수가 맵 크기보다 작을 수 없습니다." }
    }

    companion object {
        fun of(height: Int, width: Int, mineCount: Int): MineGameCondition {
            return MineGameCondition(PositiveNumber(height), PositiveNumber(width), PositiveNumber(mineCount))
        }
    }
}
