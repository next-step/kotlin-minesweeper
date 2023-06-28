package minesweeper.domain


sealed class Cell(val point: Point)
class MineCell(point: Point) : Cell(point)
class ClearCell(point: Point) : Cell(point)
