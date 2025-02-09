package tdd.minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class MineCellTest : BehaviorSpec({
    given("지뢰 셀은") {
        val sut = MineCell

        `when`("열린 상태가") {
            val result = sut.isOpen()

            then("맞다") {
                result.shouldBeTrue()
            }
        }

        `when`("열면") {
            val result = sut.open()

            then("지뢰 셀이다") {
                result.shouldBeInstanceOf<MineCell>()
            }
        }

        `when`("지뢰가") {
            then("있는 상태다") {
                sut.hasMine() shouldBe true
            }
        }

        `when`("이미 열린 셀이므로") {
            val result = sut.isExpandableToAdjacent()

            then("확장해서 열 수 없는 상태다") {
                result shouldBe false
            }
        }
    }
})
