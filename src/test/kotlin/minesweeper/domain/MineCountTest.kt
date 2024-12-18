package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineCountTest : StringSpec({
    "지뢰 개수가 0 보다 크다면 유효한 지뢰 개수" {
        val mineCount = MineCount(10)
        mineCount.count shouldBe 10
    }

    "지뢰 개수가 0 이라면 예외를 던진다" {
        shouldThrow<IllegalArgumentException> {
            MineCount(0)
        }
    }
})
