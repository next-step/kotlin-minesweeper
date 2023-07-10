package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.vo.PositiveNumber

class MineBoardTest : BehaviorSpec({
    given("너비가 5이고") {
        val width = PositiveNumber(5)
        `when`("셀 50개를 전달하면") {
            val cells = List(50) { Cell.mine }

            then("높이가 10인 보드가 생성된다.") {
                val mineBoard = MineBoard.generate(width, cells)
                mineBoard.board.size shouldBe 10
            }
        }

        `when`("셀 49개를 전달하면") {
            val cells = List(49) { Cell.mine }

            then("예외가 발생한다.") {
                shouldThrow<IllegalArgumentException> {
                    MineBoard.generate(width, cells)
                }
            }
        }
    }
})
