package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec

class RowTest : FunSpec(
    {
        test("행에 최소 1개 이상의 좌표가 존재한다") {
            shouldThrow<IllegalArgumentException> { Row() }
        }
    }
)
