package minesweeper.domain.cell

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class CellTest {

    @ParameterizedTest
    @MethodSource("위치 좌표에 음수가 존재하는 케이스")
    fun `모든 공간의 위치 x, y 는 음수일 수 없다`(x: Int, y: Int) {
        assertThatThrownBy { Empty(x, y) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("cell position must be positive.")

        assertThatThrownBy { Mine(x, y) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("cell position must be positive.")
    }

    companion object {
        @JvmStatic
        fun `위치 좌표에 음수가 존재하는 케이스`() = Stream.of(
            Arguments.of(1, -1),
            Arguments.of(-1, 1),
            Arguments.of(-1, -1)
        )
    }
}
