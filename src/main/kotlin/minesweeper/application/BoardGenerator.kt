package minesweeper.application

import minesweeper.domain.PlayableBoard

fun interface BoardGenerator {
    fun generate(command: GenerateMinesweeperCommand): PlayableBoard
}
