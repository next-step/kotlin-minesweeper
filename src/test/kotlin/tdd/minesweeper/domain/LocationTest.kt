package tdd.minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class LocationTest : BehaviorSpec({
    given("보드의 너비를 받아") {
        val width = 3
        val sut = Location(row = 3, col = 3)

        `when`("위치를") {
            val result = sut.toIndex(width)

            then("인덱스로 바꿀 수 있다") {
                result shouldBe 8
            }
        }
    }

    given("인덱스와 너비를 받아") {
        val index = 8
        val width = 3

        `when`("위치를") {
            val result = Location.from(index = index, width = width)

            then("만들 수 있다") {
                result shouldBe Location(row = 3, col = 3)
            }
        }
    }

    given("보드의 영역이 3x3일 때") {
        val area = Area(height = 3, width = 3)

        `when`("위치가 보드 내부에 있는 경우") {
            val location = Location(row = 2, col = 2)

            then("유효한 위치로 확인된다") {
                location.isValid(area) shouldBe true
            }
        }

        `when`("위치가 보드의 경계선 상에 있는 경우") {
            val edgeLocations =
                listOf(
                    Location(row = 1, col = 1),
                    Location(row = 1, col = 3),
                    Location(row = 3, col = 1),
                    Location(row = 3, col = 3),
                )

            then("모든 위치가 유효한 위치로 확인된다") {
                edgeLocations.forEach { location ->
                    location.isValid(area) shouldBe true
                }
            }
        }

        `when`("위치가 보드의 경계를 벗어난 경우") {
            val invalidLocations =
                listOf(
                    Location(row = 0, col = 1),
                    Location(row = 4, col = 1),
                    Location(row = 1, col = 0),
                    Location(row = 1, col = 4),
                )

            then("모든 위치가 유효하지 않은 위치로 확인된다") {
                invalidLocations.forEach { location ->
                    location.isValid(area) shouldBe false
                }
            }
        }
    }
})
