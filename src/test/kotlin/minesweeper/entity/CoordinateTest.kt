package minesweeper.entity

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class CoordinateTest : BehaviorSpec({
    Given("x와 y가 모두 0 이상의 값으로 Coordinate 객체를 생성할 때") {

        When("x와 y가 유효한 경우") {
            val coordinate = Coordinate(5, 10)

            Then("Coordinate 객체가 정상적으로 생성된다") {
                coordinate.x shouldBe 5
                coordinate.y shouldBe 10
            }
        }

        When("x가 음수인 경우") {
            val exception = shouldThrow<IllegalArgumentException> { Coordinate(-1, 0) }

            Then("IllegalArgumentException이 발생한다") {
                exception.message shouldBe "x는 0보다 커야합니다."
            }
        }

        When("y가 음수인 경우") {
            val exception = shouldThrow<IllegalArgumentException> { Coordinate(0, -1) }

            Then("IllegalArgumentException이 발생한다") {
                exception.message shouldBe "y는 0보다 커야합니다."
            }
        }
    }

    Given("보드의 높이와 너비가 주어졌을 때 좌표 리스트를 생성할 때") {

        When("높이가 3, 너비가 4인 경우") {
            val height = Height(3)
            val width = Width(4)
            val coordinates = Coordinate.generateCoordinates(height, width)

            Then("올바른 개수의 좌표가 생성된다") {
                coordinates shouldHaveSize (height.value * width.value)
            }

            Then("모든 좌표는 유효한 범위 내에 있어야 한다") {
                coordinates.forEach { coordinate ->
                    coordinate.x shouldBe (0 until width.value).toList().find { it == coordinate.x }
                    coordinate.y shouldBe (0 until height.value).toList().find { it == coordinate.y }
                }
            }
        }

        When("높이와 너비가 1x1인 경우") {
            val coordinates = Coordinate.generateCoordinates(Height(1), Width(1))

            Then("하나의 좌표만 생성된다") {
                coordinates shouldContainExactly listOf(Coordinate(0, 0))
            }
        }
    }

    Given("Coordinate 객체가 생성되었을 때 주변 좌표를 확인할 때") {
        val coordinate = Coordinate(3, 3)

        When("adjacentCoordinates 메서드를 호출하면") {
            val adjacent = coordinate.findAdjacentCoordinates()

            Then("현재 좌표를 제외한 8개의 주변 좌표를 반환해야 한다") {
                adjacent shouldContainExactlyInAnyOrder
                    listOf(
                        Coordinate(2, 2), Coordinate(2, 3), Coordinate(2, 4),
                        Coordinate(3, 2), Coordinate(3, 4),
                        Coordinate(4, 2), Coordinate(4, 3), Coordinate(4, 4),
                    )
            }
        }

        When("isWithinBounds 메서드를 호출하여 범위 내 좌표를 확인하면") {
            val isWithin = coordinate.isWithinBounds(Width(5), Height(5))

            Then("좌표가 범위 내에 있으면 true를 반환한다") {
                isWithin shouldBe true
            }
        }

        When("isWithinBounds 메서드를 호출하여 범위 밖 좌표를 확인하면") {
            val isWithin = coordinate.isWithinBounds(Width(3), Height(3))

            Then("좌표가 범위 밖에 있으면 false를 반환한다") {
                isWithin shouldBe false
            }
        }
    }

    Given("Coordinate 객체가 경계값에 있을 때") {
        val coordinate = Coordinate(0, 0)

        When("adjacentCoordinates 메서드를 호출하면") {
            val adjacent = coordinate.findAdjacentCoordinates()

            Then("음수 좌표는 포함되지 않아야 한다") {
                adjacent shouldContainExactlyInAnyOrder
                    listOf(
                        Coordinate(0, 1),
                        Coordinate(1, 0), Coordinate(1, 1),
                    )
            }
        }

        When("isWithinBounds 메서드를 호출하여 범위 내 좌표를 확인하면") {
            val isWithin = coordinate.isWithinBounds(Width(1), Height(1))

            Then("좌표가 (0, 0)일 때도 범위 내에 포함된다") {
                isWithin shouldBe true
            }
        }
    }
})
