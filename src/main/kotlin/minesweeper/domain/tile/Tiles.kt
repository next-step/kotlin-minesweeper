package minesweeper.domain.tile

import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.state.Tile
import minesweeper.domain.tile.state.set.NotChecked
import minesweeper.domain.tile.state.set.NotMines

@JvmInline
value class Tiles(private val value: List<Tile>) {
    constructor(vararg tiles: Tile) : this(tiles.toList())

    init {
        require(value.isNotEmpty()) { "타일은 적어도 1개 이상이어야 합니다." }
        require(value.size == value.toSet().size) { "타일은 중복될 수 없습니다." }
    }

    fun getList(): List<Marking> {
        return value.map { tile -> tile.marking }
    }

    fun isMine(coordinate: Coordinate): Boolean {
        return value.find { tile -> tile.coordinate == coordinate }?.isMine ?: false
    }

    fun checkTile(coordinate: Coordinate, marking: Marking): Tiles {
        val tiles = value.map { tile -> checkTile(tile, coordinate, marking) }
        return Tiles(tiles)
    }

    fun isChecked(coordinate: Coordinate): Boolean {
        return value.find { tile -> tile.coordinate == coordinate }?.isChecked ?: false
    }

    private fun checkTile(tile: Tile, coordinate: Coordinate, marking: Marking): Tile {
        if (tile.coordinate == coordinate && tile is NotChecked) {
            return NotMines(coordinate, marking)
        }
        return tile
    }

    fun isAllOpened(): Boolean {
        val notMinesCount = value.count { tile -> tile is NotMines }
        val mineCount = value.count { tile -> tile.isMine }
        return value.size == notMinesCount + mineCount
    }
}
