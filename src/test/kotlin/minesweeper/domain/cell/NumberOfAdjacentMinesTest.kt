package minesweeper.domain.cell

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class NumberOfAdjacentMinesTest : BehaviorSpec({
    given("인접 지뢰 개수는") {
        `when`("0 ~ 8 사이의 숫자만") {
            then("허용한다") {
                listOf(-1, 9).forEach { number ->
                    shouldThrow<IllegalArgumentException> {
                        NumberOfAdjacentMines(number)
                    }
                }
            }
        }

        `when`("상수 ZERO 는") {
            val sut = NumberOfAdjacentMines.ZERO

            then("인접 지뢰 개수 0개를 뜻한다") {
                sut shouldBe NumberOfAdjacentMines(0)
            }
        }

        `when`("현재 개수에서 1개 증가한") {
            then("인접 지뢰 개수를 만들 수 있다") {
                val sut = NumberOfAdjacentMines(7)
                val result = sut.inc()
                result shouldBe NumberOfAdjacentMines(8)
            }

            then("인접 지뢰 개수 8에서 1개를 더 증가시킬 순 없다") {
                val sut = NumberOfAdjacentMines(8)
                shouldThrow<IllegalArgumentException> {
                    sut.inc()
                }
            }
        }
    }

    given("인접 지뢰 개수 0 과 인접 지뢰 개수 1이 있을 때") {
        val zero = NumberOfAdjacentMines.ZERO
        val one = NumberOfAdjacentMines(1)

        `when`("둘을 비교하면") {
            val result = one > zero

            then("인접 지뢰 개수 1이 더 크다") {
                result.shouldBeTrue()
            }
        }
    }
})
