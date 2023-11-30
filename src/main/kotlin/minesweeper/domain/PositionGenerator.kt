package minesweeper.domain

interface PositionGenerator {
    fun generate(count: Size): List<Position>
}
