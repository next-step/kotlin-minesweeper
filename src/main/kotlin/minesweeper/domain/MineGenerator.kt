package minesweeper.domain

object MineGenerator {

    // 높이와 너비로 주어진 지뢰만큼 랜덤으로 지뢰를 생성한다.
    fun generate(mineMap: MineMap, mineCount: Int): Mines {
        val mineY = (1..mineMap.height()).shuffled().take(mineCount)
        val mineX = (1..mineMap.width()).shuffled().take(mineCount)

        return Mines(mineY.zip(mineX).map { Mine(it.first, it.second) })
    }
}
