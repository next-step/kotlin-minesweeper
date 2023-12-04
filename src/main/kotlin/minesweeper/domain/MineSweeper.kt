package minesweeper.domain

import minesweeper.domain.cell.Cell

class MineSweeper(
    private val mineSweeperMap: Map<Position, Cell>
) {
    fun getRow(index: Int): List<Cell> {
        require(index < getHeight()) { "Wrong index!" }
        return mineSweeperMap.filter { (key, _) -> key.y == index }
            .toSortedMap { prev, next -> prev.x.compareTo(next.x) }.values.toList()
    }

    fun getHeight(): Int = mineSweeperMap.keys.count { (_, y) ->
        y == 0
    }
}
