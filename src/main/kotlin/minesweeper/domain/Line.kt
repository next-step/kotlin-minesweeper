package minesweeper.domain

@JvmInline
value class Line(private val minePoints: List<MinePoint>) : List<MinePoint> by minePoints
