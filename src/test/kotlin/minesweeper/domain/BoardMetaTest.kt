package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class BoardMetaTest {
    @Test
    fun `지뢰 개수가 0 이하이면 예외가 발생한다`() {
        shouldThrow<IllegalArgumentException> {
            BoardMeta(1, 1, 0)
        }
    }

    @Test
    fun `지뢰 개수가 가로 세로의 곱보다 크면 예외가 발생한다`() {
        shouldThrow<IllegalArgumentException> {
            BoardMeta(1, 1, 2)
        }
    }
}
