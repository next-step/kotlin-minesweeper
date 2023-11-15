package minesweeper.domain

class MineSweeper(val mineMap: MineMap, val mines: Mines) {

    private val minesList = mines.mines

    init {
        minesList.forEach {
            if (it.y > mineMap.height() || it.x > mineMap.width()) {
                throw IllegalArgumentException("지뢰의 위치가 지뢰지도의 범위를 벗어났습니다.")
            }
        }
    }
}
