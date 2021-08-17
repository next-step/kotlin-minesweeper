package minesweeper

interface PositionGenerator {
    fun generate(end: Int): Int
}