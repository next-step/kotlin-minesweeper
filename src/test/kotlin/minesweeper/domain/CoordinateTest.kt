package minesweeper.domain

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainAll

class CoordinateTest : DescribeSpec({

    describe("constructor") {
        context("높이와 너비가 주어지면") {
            it("0부터 높이와 너비 만큼 좌표를 생성한다") {
                val coordinates = Coordinate.listOf(2, 2)

                coordinates shouldContainAll listOf(
                    Coordinate(0, 0),
                    Coordinate(1, 0),
                    Coordinate(0, 1),
                    Coordinate(1, 1),
                )
            }
        }

        context("0 미만의 높이와 너비가 주어지면") {
            it("IllegalArgumentException 이 발생한다") {
                assertSoftly {
                    shouldThrow<IllegalArgumentException> { Coordinate(-1, 0) }
                    shouldThrow<IllegalArgumentException> { Coordinate(0, -1) }
                }
            }
        }
    }

    describe("nearCoordinate") {
        it("주변 8개의 좌표들을 구할 수 있다.") {
            val coordinate = Coordinate(2, 2)

            coordinate.nearCoordinate() shouldContainAll listOf(
                Coordinate(1, 1),
                Coordinate(1, 2),
                Coordinate(1, 3),
                Coordinate(2, 1),
                Coordinate(2, 3),
                Coordinate(3, 1),
                Coordinate(3, 2),
                Coordinate(3, 3),
            )
        }

        context("주변 8개 좌표의 좌표 값이 0보다 작을 경우") {
            it("좌표 값이 0보다 작은 좌표는 제외하고 구할 수 있다") {
                val coordinate = Coordinate(0, 0)

                coordinate.nearCoordinate() shouldContainAll listOf(
                    Coordinate(0, 1),
                    Coordinate(1, 0),
                    Coordinate(1, 1),
                )
            }
        }
    }

    describe("aroundCoordinate") {
        it("인접한 좌표들을 구할 수 있다") {
            val coordinate = Coordinate(2, 2)

            coordinate.aroundCoordinate() shouldContainAll listOf(
                Coordinate(1, 2),
                Coordinate(2, 1),
                Coordinate(2, 3),
                Coordinate(3, 2),
            )
        }
    }

    context("인접한 좌표의 좌표 값이 0보다 작을 경우") {
        it("좌표 값이 0보다 작은 좌표는 제외하고 구할 수 있다") {
            val coordinate = Coordinate(0, 0)

            coordinate.aroundCoordinate() shouldContainAll listOf(
                Coordinate(0, 1),
                Coordinate(1, 0),
            )
        }
    }
})
