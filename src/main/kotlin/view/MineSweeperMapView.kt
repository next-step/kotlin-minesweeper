package view

import domain.MineSweeperMap

object MineSweeperMapView {
    fun drawMineSweeperMap(mineSweeperMap: MineSweeperMap) {
        println("지뢰찾기 게임 시작")
        for (i in 0 until mineSweeperMap.height) {
            for (j in 0 until mineSweeperMap.width) {
                if (mineSweeperMap.map[i][j].isMine) {
                    print("* ")
                } else {
                    print("C ")
                }
            }
            println()
        }
    }
}
