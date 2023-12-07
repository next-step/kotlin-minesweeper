package minesweeper.domain

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.MineCell
import minesweeper.domain.cell.SafeCell

class MineSweeper(
    private val mineSweeperMap: Map<Position, Cell>
) {
    private val width: Int
        get() = mineSweeperMap.keys.count { (x, _) ->
            x == 0
        }

    val height: Int
        get() = mineSweeperMap.keys.count { (_, y) ->
            y == 0
        }

    fun getRow(index: Int): List<Cell> {
        require(index < height) { "Wrong index!" }
        return mineSweeperMap.filter { (key, _) -> key.y == index }
            .toSortedMap { prev, next -> prev.x.compareTo(next.x) }.values.toList()
    }

    fun openCell(position: Position): MineSweeperState {
        val targetCell = getCell(position)

        if (targetCell is MineCell) {
            return MineSweeperState.LOSE
        }

        openAdjacentCell(position)

        if (countOfMine() == countOfClosed()) {
            return MineSweeperState.WIN
        }

        return MineSweeperState.CONTINUE
    }

    private fun countOfMine() = mineSweeperMap.values.count { it is MineCell }

    private fun countOfClosed() = mineSweeperMap.values.count { !it.isOpened }

    private fun openAdjacentCell(position: Position) {
        val targetCell = getCell(position)
        targetCell.open()

        if (targetCell is SafeCell && targetCell.countOfAdjacentMine == 0) {
            Direction.eightWays.map { (dx, dy) ->
                Position(position.x + dx, position.y + dy)
            }.forEach { it ->
                if (isValidPosition(it) && !getCell(it).isOpened) {
                    openAdjacentCell(it)
                }
            }
        }
    }

    private fun isValidPosition(position: Position): Boolean {
        return (0 until width).contains(position.x) &&
            (0 until height).contains(position.y)
    }

    private fun getCell(position: Position): Cell =
        mineSweeperMap[position] ?: throw IllegalArgumentException("Wrong position!")
}
