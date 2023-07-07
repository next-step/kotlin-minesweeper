package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import minesweeper.domain.BoardGenerator
import minesweeper.domain.CellType

class BoardTest : BehaviorSpec({

    Given("높이와 너비, 지뢰 개수를 입력받아서") {
        val height = 5
        val width = 5
        val mineCount = 5
        When("지뢰찾기 보드를 만들면") {
            val board = BoardGenerator.create(height, width, mineCount)
            Then("보드가 만들어진다.") {
                board.shouldNotBeNull()
            }
            And("요청한 사이즈대로 만들어진다.") {
                board.getHeight shouldBe 5
                board.getWidth shouldBe 5
            }
            And("생성된 보드의 지뢰 총 개수는 입력과 동일하다.") {
                board.getBoardInfo.sumOf { row -> row.rowInfo.count { cell -> cell.type == CellType.Mine } } shouldBe 5
            }
        }
    }

    Given("지뢰의 개수가 보드판의 총 격자보다 큰 입력을 받아서") {
        val height = 5
        val width = 5
        val mineCount = 26
        When("보드판을 생성할 때") {
            Then("IllegalArgumentException이 발생한다.") {
                shouldThrow<IllegalArgumentException> {
                    BoardGenerator.create(height, width, mineCount)
                }
            }
        }
    }
})
