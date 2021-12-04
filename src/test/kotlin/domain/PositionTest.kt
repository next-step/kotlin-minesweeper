package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PositionTest {

    @Test
    fun `주변의 포지션을 가져오면 주변 8개의 포지션 정보를 가져온다`() {
        val position = Position(2, 2)

        val actual = position.getAroundPositions()

        assertAll(
            "주변의 포지션을 가져오면 주변 8개의 포지션 정보를 가져온다",
            { assertThat(actual.contains(Position(1, 1))).isTrue() },
            { assertThat(actual.contains(Position(1, 2))).isTrue() },
            { assertThat(actual.contains(Position(1, 3))).isTrue() },
            { assertThat(actual.contains(Position(2, 1))).isTrue() },
            { assertThat(actual.contains(Position(2, 3))).isTrue() },
            { assertThat(actual.contains(Position(3, 1))).isTrue() },
            { assertThat(actual.contains(Position(3, 2))).isTrue() },
            { assertThat(actual.contains(Position(3, 3))).isTrue() },
        )
    }
}
