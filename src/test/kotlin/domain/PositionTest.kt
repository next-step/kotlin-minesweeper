package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PositionTest {
    @CsvSource("5,5", "1,6", "10,10")
    @ParameterizedTest
    fun `위치 전체 생성시 높이 * 너비 수만큼 생성된다`(height: Int, width: Int) {
        val rectangle = Rectangle(height, width)
        Position.createAll(rectangle).size shouldBe (height * width)
    }

    @Test
    fun `position 값에 따른 좌표 정렬`() {
        val positions = listOf(
            Position(1, 1), Position(1, 0), Position(0, 1), Position(0, 0)
        )
        positions.sorted() shouldBe listOf(
            Position(0, 0), Position(0, 1), Position(1, 0), Position(1, 1)
        )
    }
}
