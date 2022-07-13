package minesweeper.dto

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class MineBoardLengthTest : FreeSpec({

    "지뢰판 길이, 높이 값이 1보다 작은 경우 예외가 발생한다." - {
        listOf(
            0,
            -1,
            -99
        ).forAll {
            "$it 은 예외 발생" {
                val exception =
                    shouldThrowExactly<IllegalArgumentException> { MineBoardLength(value = it) }
                exception.message shouldBe "지뢰판 높이, 너비 길이는 0보다 커야합니다."
            }
        }
    }
})
