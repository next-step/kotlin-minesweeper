package domain

import domain.math.PositiveNumber
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class MineSweeperInitPropertyTest : StringSpec({

    "지뢰의 개수가 셀의 개수와 같다면 RuntimeException 예외 처리를 한다" {
        shouldThrow<RuntimeException> {
            MineSweeperInitProperty(
                height = PositiveNumber(10),
                width = PositiveNumber(10),
                mineCount = PositiveNumber(100)
            )
        }
    }

    "지뢰의 개수가 셀의 개수보다 크다면 RuntimeException 예외 처리를 한다" {
        shouldThrow<RuntimeException> {
            MineSweeperInitProperty(
                height = PositiveNumber(10),
                width = PositiveNumber(10),
                mineCount = PositiveNumber(101)
            )
        }
    }
})
