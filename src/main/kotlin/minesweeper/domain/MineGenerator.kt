package minesweeper.domain

object MineGenerator {

    // 높이와 너비로 주어진 지뢰만큼 랜덤으로 지뢰를 생성한다.
    fun generate(mineMap: MineMap, mineCount: Int): Mines {
        val mines = mineMap.createPosition()
            .flatten()
            .map { Mine(it) }
            .shuffled()
            .take(mineCount)
        return Mines(mines)
    }
}
