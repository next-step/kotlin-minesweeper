package minesweeper.dto

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

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

    "지뢰 개수가 0개인지 알 수 있다." - {
        listOf(
            row(MineCount(0), true),
            row(MineCount(1), false),
            row(MineCount(10), false),
            row(MineCount(99), false),
        ).forEach { (mineCount, result) ->
            "$mineCount 의 값이 0인지 물으면 $result 를 답한다." {
                mineCount.isZero() shouldBe result
            }
        }
    }
})
