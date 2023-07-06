package minesweeper

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import minesweeper.domain.BoardGenerator

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
                board.boardInfo.size shouldBe 5
                board.boardInfo[0].rowInfo.size shouldBe 5
            }
        }
    }
})
