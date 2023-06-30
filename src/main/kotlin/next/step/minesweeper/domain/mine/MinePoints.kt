package next.step.minesweeper.domain.mine

import next.step.minesweeper.domain.point.Point

@JvmInline
value class MinePoints(private val points: Set<Point>) : Set<Point> by points {
    companion object {
        fun of(points: Set<Point>): MinePoints = MinePoints(points)
    }
}