package minesweeper.domain

data class Position(val x: Size, val y: Size) {
    constructor(x: Int, y: Int) : this(Size(x), Size(y))
}
