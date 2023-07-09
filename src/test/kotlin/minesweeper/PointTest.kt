package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import minesweeper.domain.Point
import org.junit.jupiter.api.Test

class PointTest {
    @Test
    fun `좌표정보는 height와 width 의 최소 값 조건을 따른다`() {
        shouldThrow<IllegalArgumentException> { Point(0, 1) }
        shouldThrow<IllegalArgumentException> { Point(1, 0) }
    }
}