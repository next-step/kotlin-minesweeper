package domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

internal class BoardTest : BehaviorSpec({
    Given("보드가 주어졌을 때 ") {
        val board = Board(Height(10), Width(10), MineCnt(10))
        When("정상적인 좌표의 필드를 조회하면 ") {
            Then("정상적으로 조회한다.") {
                shouldNotThrowAny {
                    board.getField(5, 5)
                }
            }
        }

        When("비정상적인 좌표의 필드를 조회하면 ") {
            Then("예외를 던진다.") {
                shouldThrow<IllegalArgumentException> {
                    board.getField(13, 13)
                }
            }
        }
    }
})
