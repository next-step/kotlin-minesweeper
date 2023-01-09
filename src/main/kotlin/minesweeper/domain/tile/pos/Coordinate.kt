package minesweeper.domain.tile.pos

import minesweeper.domain.land.state.Area
import minesweeper.domain.tile.SurroundingTiles

data class Coordinate(val positionX: Position, val positionY: Position) {
    fun getSurroundTilesCoordinate(surroundingTiles: SurroundingTiles): Coordinate? {
        return try {
            when (surroundingTiles) {
                SurroundingTiles.TopLeft -> of(positionX.value - 1, positionY.value - 1)
                SurroundingTiles.TopMiddle -> of(positionX.value, positionY.value - 1)
                SurroundingTiles.TopRight -> of(positionX.value + 1, positionY.value - 1)
                SurroundingTiles.MiddleLeft -> of(positionX.value - 1, positionY.value)
                SurroundingTiles.MiddleRight -> of(positionX.value + 1, positionY.value)
                SurroundingTiles.BottomLeft -> of(positionX.value - 1, positionY.value + 1)
                SurroundingTiles.BottomMiddle -> of(positionX.value, positionY.value + 1)
                SurroundingTiles.BottomRight -> of(positionX.value + 1, positionY.value + 1)
            }
        } catch (e: IllegalArgumentException) {
            null
        }
    }

    fun isInArea(area: Area): Boolean {
        return positionX.value < area.width && positionY.value < area.height
    }

    companion object {
        fun of(positionX: Int, positionY: Int) = Coordinate(Position(positionX), Position(positionY))
    }
}
