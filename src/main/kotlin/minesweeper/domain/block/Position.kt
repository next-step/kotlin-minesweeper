package minesweeper.domain.block

data class Position(val x: Int, val y: Int) {

    fun plusXposition(number: Int): Int {
        return this.x + number
    }

    fun plusXposition(other: Position): Int {
        return this.x + other.x
    }

    fun plusYposition(number: Int): Int {
        return this.y + number
    }

    fun plusYposition(other: Position): Int {
        return this.y + other.y
    }
}
