package mine

import map.Point

class MinePoints(
    val points: List<Point>,
) {
    fun take(mineCount: MineCount): List<Point> = points.take(mineCount.count)

    companion object {
        fun create(
            heightSize: Int,
            widthSize: Int,
        ): MinePoints {
            val minePoints =
                (0 until heightSize)
                    .flatMap { row ->
                        (0 until widthSize)
                            .map { col ->
                                Point(row to col, Mine)
                            }
                    }.shuffled()

            return MinePoints(points = minePoints)
        }
    }
}
