package minesweeper.model.map

import minesweeper.model.map.coordinate.Area
import minesweeper.model.map.coordinate.MapArea
import minesweeper.model.map.coordinate.Position
import minesweeper.utils.toInt

class MineMap private constructor(val mapArea: MapArea, val cells: Cells) : Area by mapArea {

    companion object {

        fun build(mapArea: MapArea, isMineCell: (Position) -> Boolean) = MineMap(
            mapArea = mapArea,
            cells = Cells(mapArea.map { position -> createCell(position, isMineCell(position)) })
        )

        fun randomMap(mapArea: MapArea, mineCount: Int): MineMap {
            require(mineCount in 1..mapArea.cellCount)
            val mineIndices = (0 until mapArea.cellCount).shuffled().subList(0, mineCount)
            return build(mapArea) { position -> mapArea.indexOf(position) in mineIndices }
        }

        private fun createCell(position: Position, isMineCell: Boolean): Cell {
            val cellConstructorList = listOf(Cell::Safe, Cell::Mine)
            val constructorIndex = isMineCell.toInt()
            return cellConstructorList[constructorIndex](position)
        }
    }
}
