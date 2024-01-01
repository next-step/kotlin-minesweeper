package domain.board

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.board.MineTotal

class MineTotalTest : StringSpec({
    "0보다 큰 정수로 지뢰 수 생성" {
        val count = 3

        val result = MineTotal(count)

        result.value shouldBe count
    }

    "0이하 정수로 지뢰 수 생성 불가" {
        val count = 0

        shouldThrowExactly<IllegalArgumentException> {
            MineTotal(count)
        }
    }
})
