package tdd.minesweeper.domain.strategy

interface MinePlaceStrategy {
    fun calcMinePlace(height: Int, width: Int, mineCount: Int): List<Pair<Int, Int>>
}

class RandomMinePlaceStrategy : MinePlaceStrategy {
    override fun calcMinePlace(height: Int, width: Int, mineCount: Int): List<Pair<Int, Int>> {
        val minePlace = mutableListOf<Pair<Int, Int>>()
        while (minePlace.size < mineCount) {
            val row = (0 until height).random()
            val col = (0 until width).random()
            if (minePlace.contains(row to col)) {
                continue
            }
            minePlace.add(row to col)
        }
        return minePlace
    }
}
