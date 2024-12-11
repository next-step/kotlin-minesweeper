package view

import domain.MinePosition
import domain.MineSweeperGame
import domain.MineSweeperMap

object MineSweeperGameView {
    fun drawMineSweeperMap(mineSweeperGame: MineSweeperGame) {
        println("지뢰찾기 게임 시작")
        val map = MineSweeperMap(mineSweeperGame.mines.mines.associate { Pair(MinePosition(it.getXPosition(), it.getYPosition()), true) })
        for (i in 0 until mineSweeperGame.getWidth()) {
            drawOneLine(i, mineSweeperGame.getHeight(), map)
        }
    }

    private fun drawOneLine(
        x: Int,
        height: Int,
        map: MineSweeperMap,
    ) {
        for (i in 0 until height) {
            decideShape(x, i, map)
        }
        println()
    }

    private fun decideShape(
        x: Int,
        y: Int,
        map: MineSweeperMap,
    ) {
        if (map.isMine(x, y)) {
            print("* ")
            return
        }
        print("C ")
    }
}
