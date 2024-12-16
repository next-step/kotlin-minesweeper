package mine

import element.Mine
import map.Height
import map.Map
import map.Point
import map.Width

class MinePoints(
    val points: List<Point>,
) {
    companion object {
        fun create(
            height: Height,
            width: Width,
            mineCount: MineCount,
        ): MinePoints =
            MinePoints(
                Map
                    .create(height = height, width = width, element = Mine.ready())
                    .grid
                    .shuffle(mineCount),
            )
    }
}
