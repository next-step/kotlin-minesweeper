package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CoordinateTest {

    @Test
    fun `row, col 좌표를 갖는 Coordinate data 클래스`() {
        shouldNotThrow<Exception> { Coordinate(1, 2) }
    }

    @Test
    fun `Coordinate 사이에 + 연산자를 사용하면 각 row, col를 더한 좌표가 생성된다`() {
        val coordinate1 = Coordinate(1, 2)
        val coordinate2 = Coordinate(3, 4)

        val coordinate3 = coordinate1 + coordinate2

        coordinate3.row shouldBe 4
        coordinate3.col shouldBe 6
    }

    @Test
    fun `height와 width를 입력하여, 현재 좌표가 범위를 벗어났는지 판단할 수 있다`() {
        val height = 3
        val width = 3

        Coordinate(0, 0).isOutOfBound(height, width) shouldBe false
        Coordinate(0, 1).isOutOfBound(height, width) shouldBe false
        Coordinate(0, 2).isOutOfBound(height, width) shouldBe false
        Coordinate(1, 0).isOutOfBound(height, width) shouldBe false
        Coordinate(1, 1).isOutOfBound(height, width) shouldBe false
        Coordinate(1, 2).isOutOfBound(height, width) shouldBe false
        Coordinate(2, 0).isOutOfBound(height, width) shouldBe false
        Coordinate(2, 1).isOutOfBound(height, width) shouldBe false
        Coordinate(2, 2).isOutOfBound(height, width) shouldBe false
        Coordinate(-1, 0).isOutOfBound(height, width) shouldBe true
        Coordinate(-1, -1).isOutOfBound(height, width) shouldBe true
        Coordinate(2, 3).isOutOfBound(height, width) shouldBe true
        Coordinate(3, 3).isOutOfBound(height, width) shouldBe true
    }
}
