package minesweeper

import minesweeper.domain.land.state.Area
import minesweeper.domain.land.state.Size
import minesweeper.domain.tile.Marking
import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.state.Tile

fun Mine(positionX: Int, positionY: Int): Tile {
    return minesweeper.domain.tile.state.set.Mine(Coordinate.of(positionX, positionY))
}

fun NotChecked(positionX: Int, positionY: Int, isMine: Boolean): Tile {
    return minesweeper.domain.tile.state.set.NotChecked(Coordinate.of(positionX, positionY), isMine)
}

fun NotMines(positionX: Int, positionY: Int, marking: Marking): Tile {
    return minesweeper.domain.tile.state.set.NotMines(Coordinate.of(positionX, positionY), marking)
}

fun Area(widht: Int, height: Int): Area = Area(Size(widht), Size(height))
