package minesweeper.domain

data class BoardSize(val height: LengthNumber, val width: LengthNumber) {

    constructor(height: String, width: String) : this(
        LengthNumber(height), LengthNumber(width)
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BoardSize

        if (height != other.height) return false
        if (width != other.width) return false

        return true
    }

    override fun hashCode(): Int {
        var result = height.hashCode()
        result = 31 * result + width.hashCode()
        return result
    }
}
