package minesweeper.model.map

import minesweeper.model.map.coordinate.MapArea
import minesweeper.model.map.coordinate.Position
import minesweeper.utils.toInt

class MineMap private constructor(val mapArea: MapArea, private val cellList: List<Cell>) : List<Cell> by cellList {

    val cellCount: Int by lazy { this.count() }
    val mineCount: Int by lazy { this.count { it is Cell.MineCell } }
    val safeCellCount: Int by lazy { this.count { it is Cell.SafeCell } }

    companion object {

        fun build(mapArea: MapArea, isMineCell: (Position) -> Boolean): MineMap {

            val cellConstructorList = listOf(Cell::SafeCell, Cell::MineCell)
            val cells = List(mapArea.area) { index ->
                val position = mapArea[index]
                val isThisCellMine = isMineCell(position)
                cellConstructorList[isThisCellMine.toInt()](position)
            }
            return MineMap(mapArea, cells)
        }

        fun randomMap(mapArea: MapArea, mineCount: Int): MineMap {
            require(mineCount in 1..mapArea.area)
            val mineIndices = (0 until mapArea.area).shuffled().subList(0, mineCount)
            return build(mapArea) { position -> mapArea.indexOf(position) in mineIndices }
        }
    }
}
