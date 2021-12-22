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

    @Test
    fun `주변 8개의 포지션중에 좌표의 값이 음수라면 제외하고 가져온다`() {
        val position = Position(0, 0)

        val actual = position.getAroundPositions()

        assertAll(
            "주변의 포지션을 가져오면 주변 8개의 포지션 정보를 가져온다",
            { assertThat(actual.size).isEqualTo(3) },
            { assertThat(actual.contains(Position(0, 1))).isTrue() },
            { assertThat(actual.contains(Position(1, 0))).isTrue() },
            { assertThat(actual.contains(Position(1, 1))).isTrue() },
        )
    }
}
