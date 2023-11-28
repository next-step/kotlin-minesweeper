package view

import model.GameMap
import model.MinePoint

object OutputView {
    fun renderMap(map: GameMap) {
        println("지뢰찾기 게임 시작")
        map.map.forEach { col ->
            col.forEach(renderColum)
            println()
        }
    }

    private val renderColum = { it: MinePoint ->
        if (it.isMine) {
            print("* ")
        } else {
            print("${it.mineCountNear.toString()} ")
        }
    }
}
