package minesweeper.application

import minesweeper.domain.Board
import minesweeper.domain.Cell

class StubMinesweeperGenerator private constructor(
    val board: Board,
) : BoardGenerator {
    override fun generate(command: GenerateMinesweeperCommand): Board = board

    companion object {
        fun from(vararg cells: Cell): StubMinesweeperGenerator =
            StubMinesweeperGenerator(
                Board(cells.toSet()),
            )
    }
}
