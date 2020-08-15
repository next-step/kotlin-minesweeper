package minesweeper.domain

data class BoardSize(val height: LengthNumber, val width: LengthNumber) {
    constructor(height: String, width: String) : this(
        LengthNumber(height), LengthNumber(width)
    )
}
