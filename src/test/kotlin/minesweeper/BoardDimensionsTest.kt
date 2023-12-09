package minesweeper

import minesweeper.exception.BoardSizeOverException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.board.BoardDimensions
import minesweeper.board.Height
import minesweeper.board.Width

class BoardDimensionsTest : BehaviorSpec({

    val boardDimensions = BoardDimensions(
        Height(10),
        Width(5)
    )

    Given("stringToPosition는 사용자의 셀 위치 데이터가 주어졌을 때") {
        When("입력받은 내용이 ,로 구분되지 않거나 두 개의 정수가 아니라면") {
            val input = "1"
            Then("입력이 잘못되었다는 예외를 반환한다") {
                val result = boardDimensions.stringToPosition(input)
                result.isFailure shouldBe true
                shouldThrow<IllegalArgumentException> { result.getOrThrow() }
            }
        }

        When("정수로 이루어져있지 않다면") {
            val input = "a, b"
            Then("정수로 형변환에 실패했다는 예외를 반환한다.") {
                val result = boardDimensions.stringToPosition(input)
                result.isFailure shouldBe true
                shouldThrow<NumberFormatException> { result.getOrThrow() }
            }
        }

        When("게임판의 너비 또는 높이를 초과하였다면") {
            val input = "11, 5"
            Then("게임판 사이즈를 초과했다는 예외를 반환한다.") {
                val result = boardDimensions.stringToPosition(input)
                result.isFailure shouldBe true
                shouldThrow<BoardSizeOverException> { result.getOrThrow() }
            }
        }
    }
})
