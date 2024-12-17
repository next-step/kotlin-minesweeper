package minesweeper.application

import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.Coordinate
import minesweeper.domain.PlayableBoard

class StubMinesweeperGenerator private constructor(
    val board: Board,
) : BoardGenerator {
    override fun generate(command: GenerateMinesweeperCommand): Board = board

    companion object {
        fun from(vararg cells: Pair<Coordinate, Cell>) =
            StubMinesweeperGenerator(
                PlayableBoard(cells.toMap()),
            )
    }
}
