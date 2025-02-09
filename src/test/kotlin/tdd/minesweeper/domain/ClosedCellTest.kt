package tdd.minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class ClosedCellTest : BehaviorSpec({
    given("닫힌 셀은") {
        val sut = ClosedCell()

        `when`("열린 상태가") {
            val result = sut.isOpen()

            then("아니다") {
                result.shouldBeFalse()
            }
        }

        `when`("기본적으로") {
            then("지뢰가 없는 상태다") {
                sut.hasMine() shouldBe false
            }
        }

        `when`("인접 지뢰 수를") {
            val newAdjacentMines = AdjacentMines(8)
            val result: ClosedCell = sut.withAdjacentMines(newAdjacentMines)

            then("업데이트 할 수 있다") {
                result.adjacentMines shouldBe newAdjacentMines
            }
        }
    }

    given("지뢰가 있는 닫힌 셀은") {
        val sut =
            ClosedCell(
                hasMine = true,
            )

        `when`("열면") {
            val result = sut.open()

            then("지뢰 셀이 된다") {
                result.shouldBeInstanceOf<MineCell>()
            }
        }

        `when`("지뢰를 가지고 있으므로") {
            val result = sut.isExpandableToAdjacent()

            then("확장해서 열 수 없는 상태다") {
                result shouldBe false
            }
        }
    }

    given("지뢰가 없을 때") {
        `when`("열면") {
            then("똑같은 인접한 지뢰 수를 가진 숫자 셀이 된다") {
                (0..8).forEach { adjacentMines ->
                    val sut =
                        ClosedCell(
                            hasMine = false,
                            adjacentMines = AdjacentMines(adjacentMines),
                        )

                    val result = sut.open()

                    result.adjacentMines shouldBe sut.adjacentMines
                }
            }
        }
    }

    given("인접한 지뢰가 있는 닫힌 셀은") {
        val sut = ClosedCell(adjacentMines = 1)

        `when`("주변에 지뢰가 있으므로") {
            val result = sut.isExpandableToAdjacent()

            then("확장해서 열 수 없는 상태다") {
                result shouldBe false
            }
        }
    }

    given("인접한 지뢰가 없는 닫힌 셀은") {
        val sut = ClosedCell()

        `when`("주변에 지뢰가 없으므로") {
            val result = sut.isExpandableToAdjacent()

            then("확장해서 열 수 있는 상태다") {
                result shouldBe true
            }
        }
    }
})
