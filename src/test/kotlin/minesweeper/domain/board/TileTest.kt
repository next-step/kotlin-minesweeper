package minesweeper.domain.board

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.vo.Position

class TileTest : StringSpec({
    "주변 칸 중 지뢰가 포함된 칸의 수를 구할 수 있다." {
        val neighborCells = listOf(
            Cell(Mine.INACTIVE, Position.of(1, 0)),
            Cell(Mine.INACTIVE, Position.of(0, 1)),
            Cell(Mine.ACTIVE, Position.of(1, 1))
        )
        val tile = Tile(Cell(Mine.ACTIVE, Position.of(0, 0)), neighborCells)
        tile.getNeighborMineCount() shouldBe 1
    }
})
