package domain

import domain.vo.Point

fun createMine(x: Int, y: Int) = Mine(Coordinate(Point(x), Point(y)))

fun createEmpty(x: Int, y: Int) = Empty(Coordinate(Point(x), Point(y)))

fun createRow(size: Int, y: Int = 1) = Row.of((1..size).map { createMine(it, y) })

fun createRow(vararg cells: Cell) = Row.of(cells.toList())
