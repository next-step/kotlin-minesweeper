package domain

import vo.Point

class Row(cells: List<Cell>) {

    val cells: List<Cell> = cells.sortedBy { it.x }

    val size: Int = cells.size

    init {
        require(cells.isNotEmpty()) { "하나 이상의 cell 이 필요합니다" }

        val ys = cells.map { it.y }
        require(ys.distinct().size == 1) { "열의 y 좌표가 다릅니다" }

        val xs = cells.map { it.x }
        val expectedXs = (1..size).toList().map(::Point)
        require(xs.containsAll(expectedXs)) { "x 좌표는 1부터 순서대로 존재해야 합니다" }
    }
}
