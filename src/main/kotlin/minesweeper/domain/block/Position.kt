package minesweeper.domain.block

data class Position(val x: Int, val y: Int) {

    fun plusXposition(number: Int): Int {
        return this.x + number;
    }

    fun plusXposition(other: Position): Int {
        return this.x + other.x;
    }

    fun plusYposition(number: Int): Int {
        return this.y + number;
    }

    fun plusYposition(other: Position): Int {
        return this.y + other.y;
    }

    companion object {
        const val MINIMUM_POSITION = 0
        const val MINIMUM_POSITION_REQUIRED = "0보다 커야합니다"
    }
}
