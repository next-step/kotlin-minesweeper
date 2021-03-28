package minesweeper.domain.board

import minesweeper.domain.position.Coordinate
import minesweeper.domain.position.Position
import minesweeper.domain.tile.Blank
import minesweeper.domain.tile.Mine

object BoardFactory {

    fun create(height: Int, width: Int, mineCount: Int): Board {
        require(height > 0 && width > 0) { "높이와 너비는 0보다 커야한다. 높이: $height, 너비: $width" }

        require(mineCount > 0) {
            "지뢰 개수는 0보다 커야한다."
        }

        val tileCount = height * width
        require(tileCount >= mineCount) {
            "지뢰의 개수는 총 타일의 개수보다 같거나 작아야 한다 total: $tileCount, mine: $mineCount "
        }

        val mines = (1..mineCount).map { Mine() }

        val blankCount = tileCount - mineCount
        val blanks = (1..blankCount).map { Blank() }

        val tiles = (mines + blanks).shuffled()

        val positions = (1..width).flatMap { x ->
            (1..height).map { y ->
                Position(x = Coordinate.of(x), y = Coordinate.of(y))
            }
        }

        val elements = positions.mapIndexed { index, position -> position to tiles[index] }
            .toMap()
        return Board(elements)
    }
}
