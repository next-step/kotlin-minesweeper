package domain

import vo.Point

fun createCell(x: Int, y: Int) = Mine(Coordinate(Point(x), Point(y)))

fun createRow(size: Int, y: Int = 1) = Row((1..size).map { createCell(it, y) })
