package domain

class Board(boardGenerator: BoardGenerator) {
    val cells: Cells = boardGenerator.create()
}
