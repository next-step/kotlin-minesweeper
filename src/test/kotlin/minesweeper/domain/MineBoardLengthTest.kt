package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll

internal class MineBoardLengthTest : FreeSpec({

    "값이 1보다 작은 경우 예외가 발생한다." - {
        listOf(
            0,
            -1,
            -99
        ).forAll {
            "$it 은 예외 발생" {
                shouldThrowExactly<IllegalArgumentException> { MineBoardLength(value = it) }
            }
        }
    }
})
