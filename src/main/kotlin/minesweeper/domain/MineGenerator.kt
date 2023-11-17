package minesweeper.domain

object MineGenerator {

    // 높이와 너비로 주어진 지뢰만큼 랜덤으로 지뢰를 생성한다.
    fun generate(mineSweeperMap: MineSweeperMap, mineCount: Int): Mines {
        val mines = mineSweeperMap.createPosition()
            .map { Mine(it) }
            .shuffled()
            .take(mineCount)
        return Mines(mines)
    }
}
