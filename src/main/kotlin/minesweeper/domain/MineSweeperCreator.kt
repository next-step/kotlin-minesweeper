package minesweeper.domain

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.MineCell
import minesweeper.domain.cell.SafeCell

class MineSweeperCreator(
    private val mineSweeperSize: MineSweeperSize,
    private val minePosition: List<Position>
) {
    fun createMineSweeperMap(): Map<Position, Cell> {
        return (0 until mineSweeperSize.getArea()).map {
            Position(it % mineSweeperSize.width, it / mineSweeperSize.height)
        }
            .associateWith { position ->
                if (minePosition.contains(position)) {
                    MineCell()
                } else {
                    SafeCell(countAdjacentMine(position))
                }
            }
    }

    private fun countAdjacentMine(position: Position): Int {
        return Direction.eightWays.count { (dx, dy) ->
            val newX = position.x + dx
            val newY = position.y + dy

            (0 until mineSweeperSize.width).contains(newX) &&
                (0 until mineSweeperSize.height).contains(newY) &&
                minePosition.contains(Position(newX, newY))
        }
    }
}
