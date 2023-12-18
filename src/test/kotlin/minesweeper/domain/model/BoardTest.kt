package minesweeper.domain.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

private fun Board(width: Int, height: Int, mineCount: Int): Board {
    return Board.create(Width.from(width), Height.from(height), MineCount.from(mineCount))
}
class BoardTest : BehaviorSpec({

    given("보드가 주어지고") {
        `when`("높이가 10, 넓이가 10, 지뢰수가 100이면") {
            then("보드의 모든 셀이 지뢰여서 IllegalArgumentException 예외가 발생해야 한다.") {
                shouldThrow<IllegalArgumentException> {
                    Board(10, 10, 100)
                }
            }
        }
    }
})
