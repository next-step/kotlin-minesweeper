package minesweeper.application

import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.Coordinate

class StubMinesweeperGenerator private constructor(
    val board: Board,
) : BoardGenerator {
    override fun generate(command: GenerateMinesweeperCommand): Board = board

    companion object {
        fun from(vararg cells: Pair<Coordinate, Cell>) =
            StubMinesweeperGenerator(
                Board(cells.toMap()),
            )
    }
}
