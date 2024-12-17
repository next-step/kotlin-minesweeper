package view

import domain.MineSweeperMap

object MineSweeperMapView {
    fun drawMineSweeperMap(
        mineSweeperMap: MineSweeperMap,
        drawPolicy: DrawPolicy = DefaultDrawPolicy(),
    ) {
        for (i in 0 until mineSweeperMap.getWidth().times(mineSweeperMap.getHeight())) {
            drawPolicy.draw(mineSweeperMap, i)
        }
    }

    fun drawStart() {
        println("지뢰찾기 게임 시작")
    }
}
