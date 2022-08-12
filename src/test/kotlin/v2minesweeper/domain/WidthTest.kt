package v2minesweeper.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec

class WidthTest : FunSpec({
    test("너비가 양수가 아니면 예외를 발생시킨다.") {
        listOf(
            -1,
            0,
        ).forEach {
            // when // then
            shouldThrowExactly<IllegalArgumentException> { Width(it) }
        }
    }
})
