package minesweeper.position

fun interface PositionGenerateStrategy {
    fun generate(): Position
}
