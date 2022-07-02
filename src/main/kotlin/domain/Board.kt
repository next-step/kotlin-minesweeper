package domain

class Board(boardGenerator: BoardGenerator) {
    val cells: List<Cell> = boardGenerator.create()
}
