package com.nextstep.minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class BoardTest : BehaviorSpec({

    Given("1 미만의 높이, 너비가 주어지고") {
        When("Board 를 생성하면") {
            Then("예외가 발생한다") {
                forAll(
                    row(1, 2),
                    row(2, 1),
                    row(1, 1),
                ) { height, width ->
                    shouldThrow<IllegalArgumentException> { Board(height, width, 1) } shouldHaveMessage
                        "높이와 너비는 1보다 커야합니다. height: $height, width: $width"
                }
            }
        }
    }

    Given("지뢰 개수로 1미만의 수 혹은 보드의 사이즈보다 크거나 같은 수가 주어지고") {
        When("Board 를 생성하면") {
            Then("예외가 발생한다") {
                forAll(
                    row(2, 2, 0),
                    row(2, 2, 4),
                ) { height, width, numberOfMines ->
                    shouldThrow<IllegalArgumentException> { Board(height, width, numberOfMines) } shouldHaveMessage
                        "지뢰의 수는 1보다 크거나 같고, 입력한 보드의 크기보다 작아야합니다. numberOfMines: $numberOfMines"
                }
            }
        }
    }

    Given("높이, 너비, 지뢰의 개수가 1보다 크고, 지뢰 개수가 보드의 사이즈보다 작을 때") {
        When("Board 를 생성하면") {
            Then("초기화된 보드가 생성된다") {
                shouldNotThrowAny { Board(5, 5, 3) }
            }

            Then("지뢰 개수만큼 지뢰가 배치된다") {
                val board = Board(5, 5, 3)
                val cells = board.cells
                val numberOfMines = cells.flatMap { it.asIterable() }.count { it == 1 }

                numberOfMines shouldBe 3
            }
        }
    }
})
