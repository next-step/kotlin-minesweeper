package minesweeper.domain.vo

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll

class WidthTest : StringSpec({
    "너비를 나타내는 객체를 생성할수 있다." {
        shouldNotThrow<Throwable> { Width(1) }
    }

    "너비가 양수가 아닐 경우 Exception을 던진다." {
        listOf(0, -1).forAll {
            shouldThrow<IllegalArgumentException> { Width(it) }
        }
    }
})
