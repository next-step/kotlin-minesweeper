package minesweeper.domain

class MineSweeper(val mineMap: MineMap, val mines: List<Mine>) {

    init {
        mines.forEach {
            if (it.x > mineMap.height() || it.y > mineMap.width()) {
                throw IllegalArgumentException("지뢰의 위치가 지뢰지도의 범위를 벗어났습니다.")
            }
        }
    }
}
