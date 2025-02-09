package tdd.minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class AdjacentMinesTest : BehaviorSpec({
    given("인접 지뢰 수는") {
        `when`("0 ~ 8 의 값만") {
            then("허용한다") {
                listOf(-1, 9).forEach { adjacentMines ->
                    shouldThrow<IllegalArgumentException> { AdjacentMines(adjacentMines) }
                }
            }
        }

        `when`("현재 수에서") {
            then("1씩 증가할 수 있다") {
                (1..7).forEach { current ->
                    val sut = AdjacentMines(current)
                    val result = sut.inc()
                    result shouldBe AdjacentMines(current + 1)
                }
            }
        }

        `when`("현재 수가 8이면") {
            val sut = AdjacentMines(8)

            then("더이상 증가할 수 없다") {
                shouldThrow<IllegalArgumentException> { sut.inc() }
            }
        }

        `when`("크기를") {
            val sut = AdjacentMines(1)
            val other = AdjacentMines(0)

            then("서로 비교할 수 있다") {
                (sut > other) shouldBe true
                (sut < other) shouldBe false
                (sut == other) shouldBe false
            }
        }
    }
})
