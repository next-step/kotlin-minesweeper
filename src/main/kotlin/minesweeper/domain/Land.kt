package minesweeper.domain

import minesweeper.domain.tile.Marking
import minesweeper.domain.tile.Tiles
import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.pos.Position

data class Land(private val width: Position, private val height: Position, private val tiles: Tiles) {
    private var _tiles = tiles

    init {
        for (x in 0..width.value) for (y in 0..height.value) {
            val coordinate = Coordinate(Position(x), Position(y))
            _tiles = _tiles.checkTile(coordinate, toMarkingAsInt(getMineCount(coordinate)))
        }
    }

    fun getTiles(): List<Marking> {
        return _tiles.getList()
    }

    fun getWidth() = width.getCalibratedPosition()

    fun getMineCount(coordinate: Coordinate): Int {
        var mineCount = 0
        val positionX = coordinate.getPositionX()
        val positionY = coordinate.getPositionY()

        if (isExistMine(positionX - 1, positionY - 1)) mineCount++
        if (isExistMine(positionX - 1, positionY)) mineCount++
        if (isExistMine(positionX - 1, positionY + 1)) mineCount++
        if (isExistMine(positionX, positionY - 1)) mineCount++
        if (isExistMine(positionX, positionY + 1)) mineCount++
        if (isExistMine(positionX + 1, positionY - 1)) mineCount++
        if (isExistMine(positionX + 1, positionY)) mineCount++
        if (isExistMine(positionX + 1, positionY + 1)) mineCount++

        return mineCount
    }

    private fun isExistMine(positionX: Int, positionY: Int): Boolean {
        return if (positionX < ZERO || positionX > width.value || positionY < ZERO || positionY > height.value) {
            false
        } else {
            _tiles.isMine(Coordinate.of(positionX, positionY))
        }
    }

    private fun toMarkingAsInt(mineCount: Int): Marking {
        return when (mineCount) {
            0 -> Marking.EMPTY
            1 -> Marking.ONE
            2 -> Marking.TWO
            3 -> Marking.THREE
            4 -> Marking.FOUR
            5 -> Marking.FIVE
            6 -> Marking.SIX
            7 -> Marking.SEVEN
            8 -> Marking.EIGHT
            else -> throw IllegalArgumentException("지뢰 개수는 0~8개만 가능합니다.")
        }
    }

    companion object {
        private const val ZERO = 0

        fun of(width: Int, height: Int, tile: Tiles): Land {
            return Land(Position(width), Position(height), tile)
        }
    }
}
