package domain

class Board(val cells: Cells) {
    constructor(boardGenerator: BoardGenerator) : this(boardGenerator.create())
}
