package domain

import domain.vo.Point

class Row private constructor(val cells: List<Cell>) {

    val size: Int = cells.size

    companion object {
        fun of(cells: List<Cell>): Row {
            require(cells.isNotEmpty()) { "하나 이상의 cell 이 필요합니다" }

            val ys = cells.map { it.y }
            require(ys.distinct().size == 1) { "열의 y 좌표가 다릅니다" }

            val xs = cells.map { it.x }
            val expectedXs = (1..xs.size).toList().map(::Point)
            require(xs.containsAll(expectedXs)) { "x 좌표는 1부터 순서대로 존재해야 합니다" }

            return Row(cells.sortedBy { it.x })
        }
    }
}
