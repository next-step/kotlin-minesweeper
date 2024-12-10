package view

import domain.MineSweeperGame

object MineSweeperGameView {
    fun drawMineSweeperMap(mineSweeperGame: MineSweeperGame) {
        println("지뢰찾기 게임 시작")
        val map = mineSweeperGame.mines.associate { Pair(Pair(it.x, it.y), true) }
        for (i in 0 until mineSweeperGame.getWidth()) {
            drawOneLine(i, mineSweeperGame.getHeight(), map)
        }
    }

    private fun drawOneLine(
        width: Int,
        height: Int,
        map: Map<Pair<Int, Int>, Boolean>,
    ) {
        for (i in 0 until height) {
            decideShape(width, i, map)
        }
        println()
    }

    private fun decideShape(
        width: Int,
        height: Int,
        map: Map<Pair<Int, Int>, Boolean>,
    ) {
        if (map.getOrDefault(Pair(width, height), false)) {
            print("* ")
            return
        }
        print("C ")
    }
}
