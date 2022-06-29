package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec

class HeightTest : StringSpec({
    "높이 값으로 0이하의 정수를 입력하면 예외를 발생시킨다." {
        listOf(
            0,
            -1,
        ).forEach {
            // when //then
            shouldThrowExactly<IllegalArgumentException> { Height(it) }
        }
    }
})
