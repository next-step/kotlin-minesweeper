package minesweeper.domain.cell

import minesweeper.domain.point.Point

class CellNotFondException(point: Point) : RuntimeException("지정한 위치에 셀을 찾을 수 없습니다. $point")
