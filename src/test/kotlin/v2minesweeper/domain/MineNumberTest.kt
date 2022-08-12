package v2minesweeper.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec

class MineNumberTest : FunSpec({
    test("지뢰 갯수가 음수인 경우 예외를 발생시킨다.") {
        // given
        val invalidValue = -1

        // when // then
        shouldThrowExactly<IllegalArgumentException> { MineNumber(invalidValue) }
    }
})
