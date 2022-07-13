package minesweeper.dto

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll

internal class MineCountTest : FreeSpec({

    "지뢰 개수 값이 0보다 작은 경우 예외가 발생한다." - {
        listOf(
            -1,
            -2,
            -99
        ).forAll {
            "$it 은 예외 발생" {
                shouldThrowExactly<IllegalArgumentException> { MineBoardLength(value = it) }
            }
        }
    }
})
