package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class OpenStatusTest : StringSpec({
    "지뢰가 없다면 주위 지뢰 숫자를 반환한다" {
        Spot.VALIDATE_NEAR_MINE_COUNT.forEach {mineCount ->
            OpenStatus.from(mineCount, false).symbol shouldBe mineCount.toString()
        }
    }

    "지뢰가 있다면 주뢰 지뢰 숫자가 어떻든 지뢰를 반환한다" {
        Spot.VALIDATE_NEAR_MINE_COUNT.forEach {mineCount ->
            OpenStatus.from(mineCount, true) shouldBe OpenStatus.MINE
        }
    }

    "지뢰 숫자 범위를 넘어선 숫자에 대해선 예외가 발생한다" {
        listOf(-1, 9).forEach { mineCount ->
            shouldThrow<IllegalStateException> {
                OpenStatus.from(mineCount, false)
            }
        }
    }
})
