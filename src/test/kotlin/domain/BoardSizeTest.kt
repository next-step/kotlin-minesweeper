package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec

class BoardSizeTest : FunSpec({
    context("보드 너비는 0보다 커야합니다.") {
        shouldThrow<IllegalArgumentException> {
            BoardSize(10, 0)
        }
    }

    context("보드 높이는 0보다 커야합니다.") {
        shouldThrow<IllegalArgumentException> {
            BoardSize(0, 10)
        }
    }
})
