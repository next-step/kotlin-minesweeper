package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll

class BoardSizeTest : FunSpec({

    test("width 와 height 가 0보다 크지 않을 경우 예외가 발생한다.") {
        listOf(Pair(0, 0), Pair(3, 0), Pair(0, 2))
            .forAll {
                shouldThrow<IllegalArgumentException> { BoardSize(it.first, it.second) }
            }
    }
})
