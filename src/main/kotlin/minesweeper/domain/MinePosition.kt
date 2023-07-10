package minesweeper.domain

class MinePosition private constructor(val x: Int, val y: Int) {
    companion object {
        fun of(positionX: Int, positionY: Int): MinePosition {

            require(positionX > 0 && positionY > 0) {
                "위치값은 0보다 커야한다"
            }
            return MinePosition(positionX, positionY)
        }
    }
}
