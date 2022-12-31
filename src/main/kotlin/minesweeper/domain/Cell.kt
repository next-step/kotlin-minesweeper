package minesweeper.domain

sealed class Cell(val xPosition: Position, val yPosition: Position) {

    class Mine(xPosition: Position, yPosition: Position) : Cell(xPosition, yPosition) {
        companion object {
            fun init(): Mine = Mine(Position(0), Position(0))
        }
    }

    class Blank(xPosition: Position, yPosition: Position, val minCount: Int = 0) : Cell(xPosition, yPosition) {
        companion object {
            fun init(): Blank = Blank(Position(0), Position(0))
        }
    }
}
