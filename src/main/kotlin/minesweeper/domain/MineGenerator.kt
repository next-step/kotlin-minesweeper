package minesweeper.domain

interface MineGenerator {
    fun generate(count: Size): Mines
}
