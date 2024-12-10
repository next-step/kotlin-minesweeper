package minesweeper.entity

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class CoordinateTest : BehaviorSpec({
    Given("Coordinate 객체를 생성할 때") {
        When("x와 y가 모두 0 이상의 값인 경우") {
            Then("Coordinate 객체가 정상적으로 생성된다") {
                val coordinate = Coordinate(5, 10)
                coordinate.x shouldBe 5
                coordinate.y shouldBe 10
            }
        }

        When("x가 음수인 값인 경우") {
            Then("예외가 발생한다") {
                val exception =
                    shouldThrow<IllegalArgumentException> {
                        Coordinate(-1, 10)
                    }
                exception.message shouldBe "x는 0보다 커야합니다."
            }
        }

        When("y가 음수인 값인 경우") {
            Then("예외가 발생한다") {
                val exception =
                    shouldThrow<IllegalArgumentException> {
                        Coordinate(10, -1)
                    }
                exception.message shouldBe "y는 0보다 커야합니다."
            }
        }
    }
    Given("보드의 높이와 너비가 주어졌을 때") {

        When("좌표 리스트를 생성하면") {
            Then("올바른 개수의 좌표가 생성된다") {
                val height = Height(3)
                val width = Width(4)

                val coordinates = Coordinate.generateCoordinates(height, width)

                coordinates shouldHaveSize (height.value * width.value)
            }

            Then("좌표는 올바른 범위 내에 있어야 한다") {
                val height = Height(3)
                val width = Width(4)

                val coordinates = Coordinate.generateCoordinates(height, width)

                coordinates.forEach { coordinate ->
                    coordinate.x shouldBe (0 until width.value).toList().find { it == coordinate.x }
                    coordinate.y shouldBe (0 until height.value).toList().find { it == coordinate.y }
                }
            }
        }

        When("높이와 너비가 1x1일 때") {
            Then("하나의 좌표만 생성된다") {
                val height = Height(1)
                val width = Width(1)

                val coordinates = Coordinate.generateCoordinates(height, width)

                coordinates shouldContainExactly listOf(Coordinate(0, 0))
            }
        }
    }
})
