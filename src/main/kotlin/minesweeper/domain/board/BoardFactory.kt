package minesweeper.domain.board

import minesweeper.domain.position.Coordinate
import minesweeper.domain.position.Position
import minesweeper.domain.tile.Blank
import minesweeper.domain.tile.Mine
import minesweeper.domain.tile.Tile

object BoardFactory {

    fun create(height: Int, width: Int, mineCount: Int): Board {
        val positions = createPositions(width = width, height = height)

        val tiles = createTiles(height * width, mineCount)

        val elements = positions.mapIndexed { index, position -> position to tiles[index] }
            .toMap()
            .toSortedMap()

        elements.filterValues { it.isMine().not() }
            .forEach {
                val currentPosition = it.key
                val nearByMineCount = currentPosition.nearByPositions()
                    .map { position -> elements[position] ?: Blank() }
                    .count { tile -> tile.isMine() }
                elements[currentPosition] = Blank(nearByMineCount)
            }

        return Board(elements)
    }

    private fun createTiles(tileCount: Int, mineCount: Int): List<Tile> {
        require(tileCount >= mineCount) {
            "지뢰의 개수는 총 타일의 개수보다 같거나 작아야 한다 total: $tileCount, mine: $mineCount"
        }

        require(mineCount > 0) { "지뢰 개수는 0보다 커야한다." }

        val mines = (1..mineCount).map { Mine() }

        val blankCount = tileCount - mineCount
        val blanks = (1..blankCount).map { Blank() }

        return (mines + blanks).shuffled()
    }

    private fun createPositions(width: Int, height: Int): List<Position> {
        require(height > 0 && width > 0) { "높이와 너비는 0보다 커야한다. 높이: $height, 너비: $width" }

        return (1..width).flatMap { x ->
            (1..height).map { y ->
                Position(x = Coordinate.of(x), y = Coordinate.of(y))
            }
        }
    }
}
