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
        return position.around().count {
            (0 until mineSweeperSize.width).contains(it.x) &&
                (0 until mineSweeperSize.height).contains(it.y) &&
                minePosition.contains(it)
        }
    }
}
