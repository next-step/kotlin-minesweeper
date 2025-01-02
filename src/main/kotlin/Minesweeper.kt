data class Minesweeper(
    val size: Size,
    val mineCount: Int,
    private val minePositionList: MutableList<Position> = mutableListOf(),
) {
    init {
        require(mineCount in 1 until size.height * size.width) {
            "지뢰의 개수는 높이와 너비의 곱 보다 작거나 같아야 합니다."
        }

        val diff = mineCount - minePositionList.size
        if (diff > 0) {
            val widthRange = (0 until size.width).shuffled().take(diff)
            val heightRange = (0 until size.height).shuffled().take(diff)
            for (i in 0 until diff) {
                minePositionList.add(Position(x = widthRange[i], y = heightRange[i]))
            }
        }
    }

    fun getMineType(position: Position): MineType {
        return if (position in minePositionList) {
            MineType.MINE
        } else {
            MineType.EMPTY
        }
    }

    enum class MineType(val displayText: String) {
        EMPTY("C"),
        MINE("*"),
    }
}
