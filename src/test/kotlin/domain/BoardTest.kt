package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

internal class BoardTest : StringSpec({

    "빈 리스트로는 인스턴스 생성할 수 없다" {
        shouldThrow<IllegalArgumentException> {
            Board.of(listOf())
        }
    }

    "주어진 각 row 크기가 동일하지 않으면 인스턴스 생성할 수 없다" {
        shouldThrow<IllegalArgumentException> {
            val rows = listOf(
                createRow(3, 1),
                createRow(3, 2),
                createRow(2, 3),
            )
            Board.of(rows)
        }
    }
})
