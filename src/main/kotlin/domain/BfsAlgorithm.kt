package domain

import domain.model.Direction
import domain.model.GameMap
import domain.model.NumberTile
import domain.model.Point
import java.util.LinkedList

class BfsAlgorithm : MinesweeperAlgorithm {
    override fun openTiles(map: GameMap, point: Point) {
        val queue = LinkedList(listOf(point))
        map.field[point.y.value][point.x.value].open()

        while (queue.isNotEmpty()) {
            val currentPoint = queue.poll()
            val x = currentPoint.x.value
            val y = currentPoint.y.value

            if ((map.field[y][x] as? NumberTile)?.value != NumberTile.EMPTY) continue

            val openedTiles = unopenedTilesInSquare(map, currentPoint).onEach {
                map.field[it.y.value][it.x.value].open()
            }
            queue.addAll(openedTiles)
        }
    }

    private fun unopenedTilesInSquare(map: GameMap, point: Point): List<Point> {
        val x = point.x.value
        val y = point.y.value
        val points = mutableListOf<Point>()

        for (next in Direction.values()) {
            val nextX = x + next.x
            val nextY = y + next.y
            if (!map.inRange(nextX, nextY)) continue

            if (map.isUnopenedNumberTile(nextX, nextY)) points.add(Point.from(nextX, nextY))
        }
        return points
    }
}
