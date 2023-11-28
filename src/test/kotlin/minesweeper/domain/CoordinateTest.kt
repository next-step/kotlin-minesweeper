package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import org.junit.jupiter.api.Test

class CoordinateTest {

    @Test
    fun `x, y 좌표를 갖는 Coordinate data 클래스`() {
        shouldNotThrow<Exception> { Coordinate(1, 2) }
    }
}