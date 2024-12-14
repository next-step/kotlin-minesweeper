package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.row
import io.kotest.inspectors.forAll

class PointTest : BehaviorSpec({
    Given("좌표는") {
        When("행,열 값 중 음수가 존재하면") {
            val rows =
                listOf(
                    row(-1, 1),
                    row(1, -1),
                )
            Then("생성 시 예외가 발생한다.") {
                rows.forAll { (row, col) ->
                    shouldThrow<IllegalArgumentException> { Point(row, col) }
                }
            }
        }

        When("행,열 값이 0 이상 이면") {
            val rows =
                listOf(
                    row(0, 0),
                    row(0, 1),
                    row(1, 0),
                )
            Then("예외 없이 생성된다.") {
                rows.forAll { (row, col) ->
                    shouldNotThrow<IllegalArgumentException> { Point(row, col) }
                }
            }
        }
    }
})
