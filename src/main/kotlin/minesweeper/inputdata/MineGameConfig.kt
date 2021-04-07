package minesweeper.inputdata

class MineGameConfig(
    val height: PositiveNumber,
    val width: PositiveNumber,
    val mineCount: PositiveNumber
) {
    init {
        require(height * width >= mineCount) { "지뢰 수가 맵 크기보다 클 수 없습니다." }
    }

    fun getMapSize() = (height * width).size

    companion object {
        fun of(height: Int, width: Int, mineCount: Int): MineGameConfig {
            return MineGameConfig(PositiveNumber(height), PositiveNumber(width), PositiveNumber(mineCount))
        }
    }
}
