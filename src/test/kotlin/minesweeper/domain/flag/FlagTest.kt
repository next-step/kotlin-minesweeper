package minesweeper.domain.flag

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.throwable.shouldHaveMessage

class FlagTest : StringSpec({

    "플래그에서 상태를 지정할 수 있는 값은 0부터 8까지다." {
        forAll(
            row(10),
            row(-1),
            row(9),
        ) { mineCount ->
            val exception = shouldThrow<IllegalArgumentException> {
                BlockFlag(aroundMineCount = mineCount)
            }

            exception shouldHaveMessage "주변 지뢰는 0 ~ 8 범위의 개수만 가질 수 있습니다. 입력값 : $mineCount"
        }
    }
})
