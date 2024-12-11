package view

import domain.MineSweeperMap

object MineSweeperMapView {
    fun drawMineSweeperMap(mineSweeperMap: MineSweeperMap) {
        println("지뢰찾기 게임 시작")
        for (i in 0 until mineSweeperMap.getWidth().times(mineSweeperMap.getHeight())) {
            drawRow(mineSweeperMap, i)
        }
    }

    private fun drawRow(
        mineSweeperMap: MineSweeperMap,
        i: Int,
    ) {
        if (mineSweeperMap.isMine(i % mineSweeperMap.getWidth(), i.div(mineSweeperMap.getWidth()))) {
            print("* ")
        } else {
            print("C ")
        }
        if (i % mineSweeperMap.getWidth() == mineSweeperMap.getWidth() - 1) {
            println()
        }
    }
}
