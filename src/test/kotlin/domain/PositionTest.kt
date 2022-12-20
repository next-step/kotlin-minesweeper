package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PositionTest {
    @CsvSource("5,5,25", "1,6,6", "10,10,100")
    @ParameterizedTest(name = "x {0}, y {1} 길이로 생성하면 총 크기는 {2}이다")
    fun `위치 전체 생성시 x 곱하기 y 수만큼 생성된다`(width: Int, height: Int, size: Int) {
        val rectangle = Rectangle(Width(width), Height(height))
        Position.createAll(rectangle).size shouldBe size
    }

    @Test
    fun `position x, y 오름차순으로 정렬한다`() {
        val positions = listOf(
            Position(1, 1), Position(1, 0), Position(0, 1), Position(0, 0)
        )
        positions.sorted() shouldBe listOf(
            Position(0, 0), Position(0, 1), Position(1, 0), Position(1, 1)
        )
    }
}
