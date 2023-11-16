package minesweeper.domain

data class Position(val x: Int, val y: Int) {

    fun match(otherPosition: Position): Boolean {
        return this == otherPosition
    }
}
