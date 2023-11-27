package minesweeper.domain

interface MineGenerator {
    fun generate(count: Int): Mines
}
