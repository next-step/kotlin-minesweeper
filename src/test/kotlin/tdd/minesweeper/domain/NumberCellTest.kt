package tdd.minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class NumberCellTest : BehaviorSpec({
    given("숫자 셀은") {
        val sut = NumberCell()

        `when`("열린 상태가") {
            val result = sut.isOpen()

            then("맞다") {
                result.shouldBeTrue()
            }
        }

        `when`("열면") {
            val result = sut.open()

            then("숫자 셀이다") {
                result.shouldBeInstanceOf<NumberCell>()
            }
        }

        `when`("지뢰가") {
            then("없는 상태다") {
                sut.hasMine() shouldBe false
            }
        }

        `when`("이미 열린 셀이므로") {
            val result = sut.isExpandableToAdjacent()

            then("확장해서 열 수 없는 상태이다") {
                result shouldBe false
            }
        }
    }

    given("인접 지뢰 수는") {
        `when`("0 ~ 8 까지만") {
            then("가능하다") {
                listOf(-1, 9).forEach { adjacentLandmines ->
                    shouldThrow<IllegalArgumentException> { NumberCell(AdjacentMines(adjacentLandmines)) }
                }
                (0..8).forEach { adjacentLandmines ->
                    val sut = NumberCell(AdjacentMines(adjacentLandmines))
                    sut.adjacentMines shouldBe AdjacentMines(adjacentLandmines)
                }
            }
        }
    }
})
