package minesweeper.domain

interface PositionGenerator {

    fun generate(end: Int): Int
}
