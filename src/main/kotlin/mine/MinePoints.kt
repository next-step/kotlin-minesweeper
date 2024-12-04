package mine

import map.*

class MinePoints(
    val points: List<Point>,
) {
    fun take(mineCount: MineCount): List<Point> = points.take(mineCount.count)

    companion object {
        fun create(
            height: Height,
            width: Width,
        ): MinePoints {
            val minePoints =
                (0 until height.size)
                    .flatMap { row ->
                        (0 until width.size)
                            .map { col ->
                                Point(row.toIndex() to col.toIndex(), Mine)
                            }
                    }.shuffled()

            return MinePoints(points = minePoints)
        }
    }
}
