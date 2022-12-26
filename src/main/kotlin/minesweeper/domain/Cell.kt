package minesweeper.domain

sealed class Cell(private val xPosition: Position, private val yPosition: Position) {

    class Blank(xPosition: Int = 0, yPosition: Int = 0) : Cell(Position(xPosition), Position(yPosition))
    class Mine(xPosition: Int = 0, yPosition: Int = 0) : Cell(Position(xPosition), Position(yPosition))
}
