package minesweeper.domain.board

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Cells
import minesweeper.domain.position.MinePositionGenerator
import minesweeper.domain.position.Position

class Board private constructor(private val _cells: Cells) {

    val cells: Map<Position, Cell>
        get() = this._cells.values

    companion object {
        fun of(settings: BoardSettings, generator: MinePositionGenerator): Board {
            return Board(Cells.of(settings, generator.generate(settings)))
        }
    }
}
