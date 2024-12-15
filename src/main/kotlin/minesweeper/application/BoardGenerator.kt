package minesweeper.application

import minesweeper.domain.Board

fun interface BoardGenerator {
    fun generate(command: GenerateMinesweeperCommand): Board
}
