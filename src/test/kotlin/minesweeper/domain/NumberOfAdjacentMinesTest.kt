package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

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
    }
})
