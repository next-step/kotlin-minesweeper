package minesweeper.domain

class CellGenerator(mineCellSelector: MineCellSelector) {
    val pointOfMines: Set<Point> = mineCellSelector.select()
    fun generate(point: Point): Cell = if (pointOfMines.contains(point)) MineCell(point) else ClearCell(point)
}
