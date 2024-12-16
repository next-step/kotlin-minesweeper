package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class NumberOfAdjacentMinesTest : BehaviorSpec({
    given("NumberOfAdjacentMines 는") {
        `when`("0 ~ 8 이외의 숫자가 들어오면") {
            then("IllegalArgumentException 예외를 던진다") {
                listOf(-1, 9).forEach { number ->
                    shouldThrow<IllegalArgumentException> {
                        NumberOfAdjacentMines(number)
                    }
                }
            }
        }

        `when`("ZERO 상수는") {
            val sut = NumberOfAdjacentMines.ZERO

            then("0의 값을 가진다") {
                sut shouldBe NumberOfAdjacentMines(0)
            }
        }

        `when`("inc 연산자를 호출하면") {
            then("값이 1 증가한다") {
                val sut = NumberOfAdjacentMines(7)
                val result = sut.inc()
                result shouldBe NumberOfAdjacentMines(8)
            }

            then("최댓값인 8에서 inc 호출 시 IllegalArgumentException 예외를 던진다") {
                val sut = NumberOfAdjacentMines(8)
                shouldThrow<IllegalArgumentException> {
                    sut.inc()
                }
            }
        }
    }
})
