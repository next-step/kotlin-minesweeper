package minesweeper.domain.board

import minesweeper.domain.common.PositiveInt
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class BoardTest {

    @ParameterizedTest
    @MethodSource("보드의 높이와 너비 중 음수가 존재하는 케이스")
    fun `보드의 높이와 너비는 음수일 수 없다`(width: Int, height: Int) {
        val exception = assertThrows<IllegalArgumentException> { Board.of(PositiveInt(width), PositiveInt(height)) }
        Assertions.assertThat(exception.message).isEqualTo("property must be zero or positive.")
    }

    companion object {
        @JvmStatic
        fun `보드의 높이와 너비 중 음수가 존재하는 케이스`() = Stream.of(
            Arguments.of(-10, 10),
            Arguments.of(10, -10),
            Arguments.of(-10, -10)
        )
    }
}
