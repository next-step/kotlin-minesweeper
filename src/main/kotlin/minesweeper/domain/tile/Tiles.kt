package minesweeper.domain.tile

import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.state.Tile
import minesweeper.domain.tile.state.set.NotChecked
import minesweeper.domain.tile.state.set.NotMines

@JvmInline
value class Tiles(private val value: List<Tile>) {
    constructor(vararg tiles: Tile) : this(tiles.toList())

    val list: List<Marking>
        get() = value.map(Tile::marking)

    init {
        require(value.isNotEmpty()) { "타일은 적어도 1개 이상이어야 합니다." }
        require(value.size == value.toSet().size) { "타일은 중복될 수 없습니다." }
    }

    fun isMine(coordinate: Coordinate): Boolean = findTile(coordinate)?.isMine ?: false

    fun isChecked(coordinate: Coordinate): Boolean = findTile(coordinate)?.isChecked ?: false

    fun isAllOpened(): Boolean {
        val notMinesCount = value.count { tile -> tile is NotMines }
        val mineCount = value.count(Tile::isMine)
        return value.size == notMinesCount + mineCount
    }

    fun getMineCount(coordinate: Coordinate): Int {
        val surroundingTiles = SurroundingTiles.values()
        return surroundingTiles.count {
            coordinate.getSurroundTilesCoordinate(it)?.let(::isMine) ?: false
        }
    }

    fun checkTile(coordinate: Coordinate, marking: Marking): Tiles {
        val tiles = value.map { tile -> checkTile(tile, coordinate, marking) }
        return Tiles(tiles)
    }

    private fun checkTile(tile: Tile, coordinate: Coordinate, marking: Marking): Tile {
        if (tile.coordinate == coordinate && tile is NotChecked) {
            return NotMines(coordinate, marking)
        }
        return tile
    }

    private fun findTile(coordinate: Coordinate): Tile? = value.find { it.coordinate == coordinate }
}
