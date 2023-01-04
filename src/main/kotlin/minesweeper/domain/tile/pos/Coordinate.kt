package minesweeper.domain.tile.pos

import minesweeper.domain.tile.SurroundingTiles

data class Coordinate(private val positionX: Position, private val positionY: Position) {
    fun getSurroundTilesCoordinate(surroundingTiles: SurroundingTiles): Pair<Int, Int> {
        return when (surroundingTiles) {
            SurroundingTiles.TopLeft -> Pair(positionX.value - 1, positionY.value - 1)
            SurroundingTiles.TopMiddle -> Pair(positionX.value, positionY.value - 1)
            SurroundingTiles.TopRight -> Pair(positionX.value + 1, positionY.value - 1)
            SurroundingTiles.MiddleLeft -> Pair(positionX.value - 1, positionY.value)
            SurroundingTiles.MiddleRight -> Pair(positionX.value + 1, positionY.value)
            SurroundingTiles.BottomLeft -> Pair(positionX.value - 1, positionY.value + 1)
            SurroundingTiles.BottomMiddle -> Pair(positionX.value, positionY.value + 1)
            SurroundingTiles.BottomRight -> Pair(positionX.value + 1, positionY.value + 1)
        }
    }

    fun getPositionXY(): Pair<Int, Int> {
        return Pair(positionX.value, positionY.value)
    }

    companion object {
        fun of(positionX: Int, positionY: Int) = Coordinate(Position(positionX), Position(positionY))
    }
}
