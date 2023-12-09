package minesweeper

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.board.BoardDimensions
import minesweeper.board.Number

class BoardDimensionsTest : BehaviorSpec({

    val boardDimensions = BoardDimensions(
        Number(10),
        Number(5)
    )

    Given("stringToPosition는 사용자의 셀 위치 데이터가 주어졌을 때") {
        When("입력받은 내용이 ,로 구분되지 않거나 두 개의 정수가 아니라면") {
            val input = "1"
            Then("널을 반환한다.") {
                val result = boardDimensions.stringToPosition(input)
                result shouldBe null
            }
        }
    }
})
