package view

import domain.MineMap
import domain.MineSweeperGame

object MineSweeperGameView {
    fun drawMineSweeperMap(mineSweeperGame: MineSweeperGame) {
        println("지뢰찾기 게임 시작")
        val map = mineSweeperGame.getMineMap()
        for (i in 0 until mineSweeperGame.getWidth()) {
            drawOneLine(i, mineSweeperGame.getHeight(), map)
        }
    }

    private fun drawOneLine(
        x: Int,
        height: Int,
        map: MineMap,
    ) {
        for (i in 0 until height) {
            decideShape(x, i, map)
        }
        println()
    }

    private fun decideShape(
        x: Int,
        y: Int,
        map: MineMap,
    ) {
        if (map.isMine(x, y)) {
            print("* ")
            return
        }
        print("C ")
    }
}
