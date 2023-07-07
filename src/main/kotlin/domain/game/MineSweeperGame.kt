package domain.game

import domain.map.Coordinate
import domain.map.MineMap
import java.util.LinkedList
import java.util.Queue

class MineSweeperGame(
    private val mineMap: MineMap,
) {

    fun open(coordinate: Coordinate): OpenResult {
        if (mineMap[coordinate].isMine()) {
            return OpenResult.MineOpened
        }

        openWithAround(coordinate)

        return if (mineMap.isAllGroundCellsOpened()) {
            OpenResult.AllMineFound
        } else {
            OpenResult.GroundOpened(mineMap.capture())
        }
    }

    private fun openWithAround(coordinate: Coordinate) {
        val coordinates: Queue<Coordinate> = LinkedList(listOf(coordinate))
        while (coordinates.isNotEmpty()) {
            val currentCoordinate = coordinates.poll()
            val cell = mineMap.getHideGroundCellOrNull(currentCoordinate) ?: continue
            mineMap.open(currentCoordinate)
            if (cell.aroundMineCount.isSafeZone()) {
                coordinates.addAll(currentCoordinate.aroundCoordinatesInMap())
            }
        }
    }

    private fun Coordinate.aroundCoordinatesInMap(): List<Coordinate> {
        return aroundCoordinates().filter { coordinate ->
            mineMap.isIn(coordinate)
        }
    }
}
