package domain

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

            if ((map.field[y][x] as? NumberTile)?.value != 0) continue

            val unopenedTiles = unopenedTilesInSquare(map, currentPoint)
            unopenedTiles.forEach { map.field[it.y.value][it.x.value].open() }
            queue.addAll(unopenedTiles)
        }
    }

    private fun unopenedTilesInSquare(map: GameMap, point: Point): List<Point> {
        val x = point.x.value
        val y = point.y.value
        val points = mutableListOf<Point>()

        for (i in y - 1..y + 1) {
            if (i < 0 || i >= map.info.height) continue

            points.addAll(unopenedTilesInSquareColumn(map, x, i))
        }
        return points
    }

    private fun unopenedTilesInSquareColumn(map: GameMap, x: Int, y: Int): List<Point> {
        val points = mutableListOf<Point>()

        for (j in x - 1..x + 1) {
            if (j < 0 || j >= map.info.width) continue

            if (map.field[y][j] is NumberTile && !map.field[y][j].isOpened) points.add(Point.from(j, y))
        }
        return points
    }
}
