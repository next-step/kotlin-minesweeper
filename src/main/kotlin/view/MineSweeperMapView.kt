package view

import domain.MineSweeperMap

object MineSweeperMapView {
    fun drawMineSweeperMap(mineSweeperMap: MineSweeperMap) {
        for (i in 0 until mineSweeperMap.getWidth().times(mineSweeperMap.getHeight())) {
            drawRow(mineSweeperMap, i)
        }
    }

    fun drawStart() {
        println("지뢰찾기 게임 시작")
    }

    private fun drawRow(
        mineSweeperMap: MineSweeperMap,
        i: Int,
    ) {
        val y = i / mineSweeperMap.getWidth()
        val x = i % mineSweeperMap.getWidth()
        when (mineSweeperMap.isOpen(x, y)) {
            false -> print("C ")
            true -> if (mineSweeperMap.isMine(x, y)) print("* ") else print("${mineSweeperMap.getMineAroundCount(i)} ")
        }

        if (i % mineSweeperMap.getWidth() == mineSweeperMap.getWidth() - 1) {
            println()
        }
    }
}
