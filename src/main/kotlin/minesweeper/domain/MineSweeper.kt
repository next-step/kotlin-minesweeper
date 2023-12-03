package minesweeper.domain

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.MineCell
import minesweeper.domain.cell.SafeCell

class MineSweeper(
    val mineSweeperSize: MineSweeperSize,
    val minePosition: List<Int>
) {
    val mineMap: Map<Int, List<Cell>> = (0 until mineSweeperSize.height).associateWith { y ->
        (0 until mineSweeperSize.width).map { x ->
            if (minePosition.contains(y * mineSweeperSize.height + x)) {
                MineCell()
            } else {
                SafeCell(countAdjacentMine(x, y))
            }
        }
    }

    private fun countAdjacentMine(x: Int, y: Int): Int {
        return Direction.eightWays.count { (dx, dy) ->
            val newX = x + dx
            val newY = y + dy

            (0 until mineSweeperSize.width).contains(newX) &&
                (0 until mineSweeperSize.height).contains(newY) &&
                minePosition.contains(newX + newY * mineSweeperSize.height)
        }
    }
}
