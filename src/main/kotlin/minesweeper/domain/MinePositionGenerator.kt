package minesweeper.domain

fun interface MinePositionGenerator {
    fun generatePosition(): MinePosition
}
