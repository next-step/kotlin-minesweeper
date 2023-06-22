package minesweeper.domain

@JvmInline
value class Line(private val points: List<Point>) : List<Point> by points
