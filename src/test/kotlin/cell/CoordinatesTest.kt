package cell

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class CoordinatesTest {
    @MethodSource("provideInvalidValue")
    @ParameterizedTest
    fun `좌표 일급 컬렉션 생성 시 음수가 있으면 예외가 발생한다`(value: Length) {
        shouldThrow<IllegalArgumentException> {
            Coordinates.of(value, value)
        }
    }

    companion object {
        @JvmStatic
        fun provideInvalidValue(): List<Length> = listOf(Length(-1), Length(0))
    }
}
