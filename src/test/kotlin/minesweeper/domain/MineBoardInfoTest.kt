package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import minesweeper.domain.cell.Cell

class MineBoardInfoTest : FunSpec({

    context("init") {
        test("높이가 0이하인 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { MineBoardInfo(0, 2) }
            exception shouldHaveMessage "지뢰찾기맵 높이는 1이상이어야 합니다."
        }

        test("너비가 0이하인 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { MineBoardInfo(2, 0,) }
            exception shouldHaveMessage "지뢰찾기맵 너비는 1이상이어야 합니다."
        }
    }
})
