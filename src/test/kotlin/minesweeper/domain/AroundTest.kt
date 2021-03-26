package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AroundTest {
    @Test
    internal fun `x, y 를 입력하면 위치 주변의 x, y 를 리턴한다`() {
        assertThat(Around.aroundPositions(1, 1))
            .contains(
                Position(0, 0), Position(1, 0), Position(2, 0),
                Position(0, 1), /*      input       */ Position(2, 1),
                Position(0, 2), Position(1, 2), Position(2, 2)

            )
    }
}
