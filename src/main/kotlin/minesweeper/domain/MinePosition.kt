package minesweeper.domain

class MinePosition private constructor(val x: Position, val y: Position) {
    companion object {
        fun of(positionX: Int, positionY: Int): MinePosition {
            require(positionX >= 0 && positionY >= 0) {
                "위치값은 0보다 크거나 같아야한다"
            }
            return MinePosition(Position.of(positionX), Position.of(positionY))
        }
    }
}
