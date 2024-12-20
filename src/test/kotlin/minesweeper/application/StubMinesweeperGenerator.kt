package minesweeper.application

import minesweeper.cellsOf
import minesweeper.domain.Cell
import minesweeper.domain.Coordinate
import minesweeper.domain.PlayableBoard

class StubMinesweeperGenerator private constructor(
    val board: PlayableBoard,
) : BoardGenerator {
    override fun generate(command: GenerateMinesweeperCommand): PlayableBoard = board

    companion object {
        fun from(vararg cells: Pair<Coordinate, Cell>) =
            StubMinesweeperGenerator(
                PlayableBoard(cellsOf(*cells)),
            )
    }
}
