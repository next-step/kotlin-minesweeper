package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RectangleTest {
    @CsvSource("1,-1", "-1,1", "-1,-1")
    @ParameterizedTest
    fun `직사각형은 높이 너비가 음수이면 예외 발생`(width: Int, height: Int) {
        shouldThrow<IllegalArgumentException> {
            Rectangle(Width(width), Height(height))
        }
    }

    @CsvSource("5,5,25", "1,6,6", "10,10,100")
    @ParameterizedTest(name = "x {0}, y {1} 길이로 생성하면 총 크기는 {2}이다")
    fun `위치 전체 생성시 x 곱하기 y 수만큼 생성된다`(width: Int, height: Int, size: Int) {
        Rectangle(Width(width), Height(height)).toPositions().size shouldBe size
    }
}
