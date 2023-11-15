package minesweeper.domain

class MineSweeper(val mineMap: MineMap, mines: Mines) {

    val mineList = mines.mines

    init {
        mineList.forEach {
            if (it.x > mineMap.height() || it.y > mineMap.width()) {
                throw IllegalArgumentException("지뢰의 위치가 지뢰지도의 범위를 벗어났습니다.")
            }
        }
    }

    fun isMine(x: Int, y: Int): Boolean {
        return mineList.any { it.x == x && it.y == y }
    }
}
