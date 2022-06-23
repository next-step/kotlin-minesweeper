package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

class BoardFieldsTest : DescribeSpec({

    describe("nearFields") {
        context("좌표가 주어지면 주변 필드를 반환한다") {
            val coordinates = Coordinate.listOf(10, 10)
            val boardFields = BoardFields(coordinates.map { BoardField.nonMine(it) })

            it("주어진 좌표가 0인 경우") {
                val nearFields = boardFields.nearFields(Coordinate(0, 0))

                nearFields.boardFields shouldContainAll listOf(
                    BoardField.nonMine(Coordinate(0, 1)),
                    BoardField.nonMine(Coordinate(1, 1)),
                    BoardField.nonMine(Coordinate(1, 0)),
                )
            }

            it("주어진 좌표의 주변 좌표가 모두 있는 경우") {
                val nearFields = boardFields.nearFields(Coordinate(1, 1))

                nearFields.boardFields shouldContainAll listOf(
                    BoardField.nonMine(Coordinate(0, 0)),
                    BoardField.nonMine(Coordinate(0, 1)),
                    BoardField.nonMine(Coordinate(0, 2)),
                    BoardField.nonMine(Coordinate(1, 0)),
                    BoardField.nonMine(Coordinate(1, 2)),
                    BoardField.nonMine(Coordinate(2, 0)),
                    BoardField.nonMine(Coordinate(2, 1)),
                    BoardField.nonMine(Coordinate(2, 2)),
                )
            }
        }
    }

    describe("mineCount") {
        it("지뢰 개수를 확인할 수 있다") {
            val boardFields = BoardFields(
                listOf(
                    BoardField.mine(Coordinate(0, 0)),
                    BoardField.nonMine(Coordinate(0, 1)),
                    BoardField.mine(Coordinate(0, 2)),
                    BoardField.nonMine(Coordinate(1, 0)),
                    BoardField.nonMine(Coordinate(1, 2)),
                    BoardField.mine(Coordinate(2, 0)),
                    BoardField.nonMine(Coordinate(2, 1)),
                    BoardField.nonMine(Coordinate(2, 2)),
                )
            )

            boardFields.mineCount() shouldBe 3
        }
    }

    describe("open") {
        val boardFields = BoardFields(
            listOf(
                BoardField.mine(Coordinate(0, 0)),
                BoardField.nonMine(Coordinate(0, 1)),
                BoardField.mine(Coordinate(0, 2)),
                BoardField.nonMine(Coordinate(1, 0)),
                BoardField.nonMine(Coordinate(1, 1)),
                BoardField.nonMine(Coordinate(1, 2)),
                BoardField.mine(Coordinate(2, 0)),
                BoardField.nonMine(Coordinate(2, 1)),
                BoardField.nonMine(Coordinate(2, 2)),
            )
        )

        context("필드들에 해당하는 좌표를 입력받아") {
            it("해당하는 필드를 열 수 있다.") {
                val coordinate = Coordinate(0, 0)
                boardFields.open(coordinate)

                val boardField = boardFields.boardFields.find { it.coordinate == coordinate }!!
                boardField.isOpen shouldBe true
            }
        }

        context("필드들에 해당하지 않는 좌표를 입력받으면") {
            it("IllegalArgumentException 이 발생한다.") {
                shouldThrow<IllegalArgumentException> { boardFields.open(Coordinate(3, 3)) }
            }
        }
    }
})
