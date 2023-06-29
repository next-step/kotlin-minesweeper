data class Minesweeper(
    val size: Size,
    val mineCount: Int,
) {

    private val minePositionList = mutableListOf<Position>()

    init {
        require(size.height in 1..100) {
            "지뢰판의 높이는 1보다 크거나 같고 100보다 작거나 같아야 합니다."
        }

        require(size.width in 1..100) {
            "지뢰판의 너비는 1보다 크거나 같고 100보다 작거나 같아야 합니다."
        }

        require(mineCount in 1 until size.height * size.width) {
            "지뢰의 개수는 높이와 너비의 곱 보다 작거나 같아야 합니다."
        }

        while (minePositionList.size < mineCount) {
            val position = Position((0 until size.height).random(), (0 until size.width).random())
            if (minePositionList.contains(position)) {
                continue
            }
            minePositionList.add(position)
        }
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        for (i in 0 until size.height) {
            for (j in 0 until size.width) {
                val position = Position(i, j)
                if (minePositionList.contains(position)) {
                    stringBuilder.append("*")
                } else {
                    stringBuilder.append("C")
                }
                if (j == size.width - 1) {
                    stringBuilder.append("\n")
                } else {
                    stringBuilder.append(" ")
                }
            }
        }
        return stringBuilder.toString()
    }
}
