package minesweeper.domain.board

import minesweeper.domain.vo.Position
import minesweeper.domain.vo.PositionX
import minesweeper.domain.vo.PositionY

class Cell(val mine: Mine, val position: Position) {
    fun isMineActive(): Boolean = mine.isActive

    companion object {
        fun of(mine: Mine, x: PositionX, y: PositionY): Cell {
            return Cell(mine, Position(x, y))
        }
    }
}
