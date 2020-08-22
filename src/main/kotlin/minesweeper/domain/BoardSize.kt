package minesweeper.domain

data class BoardSize(val height: LengthNumber, val width: LengthNumber) {
    val count = height.length * width.length

    constructor(height: String, width: String) : this(
        LengthNumber(height), LengthNumber(width)
    )
    constructor() : this(
        LengthNumber(MAX_SIZE), LengthNumber(MAX_SIZE)
    )
}
