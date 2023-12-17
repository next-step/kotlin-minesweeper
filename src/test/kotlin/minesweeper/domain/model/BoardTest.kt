package minesweeper.domain.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec


fun Board(width: Int, height: Int, mineCount: Int): Board {
    return Board.create(Width.from(width), Height.from(height), MineCount.from(mineCount))
}
class BoardTest : StringSpec({

    "보드의 모든 셀이 지뢰여서는 안된다." {
        shouldThrow<IllegalArgumentException> {
            Board(10, 10, 100)
        }
    }
})
