package minesweeper.domain

internal data class Position(val x: NaturalNumber, val y: NaturalNumber) {
    init {
        require(x >= NaturalNumber.ZERO && y >= NaturalNumber.ZERO)
    }
}
