package minesweeper.domain.position

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PositionTest {

    @ParameterizedTest
    @CsvSource(
        "0, 1",
        "-1, 1",
    )
    fun `열(x) 값이 1 미만이면 IllegalArgumentException 이 발생한다`(x: Int, y: Int) {
        shouldThrow<IllegalArgumentException> {
            Position(x = x, y = y)
        }
    }

    @ParameterizedTest
    @CsvSource(
        "0, 1",
        "-1, 1",
    )
    fun `행(y) 값이 1 미만이면 IllegalArgumentException 이 발생한다`(x: Int, y: Int) {
        shouldThrow<IllegalArgumentException> {
            Position(x = x, y = y)
        }
    }

    @Test
    fun `(1,1) 위치에서 8 방향 중 유효한 위치는 (1,2), (2,2), (2,1) 이다`() {
        val x = 1
        val y = 1
        val position = Position(x = x, y = y)
        val expected = Positions(
            listOf(
                Position(x = 1, y = 2),
                Position(x = 2, y = 2),
                Position(x = 2, y = 1),
            ),
        )

        val actual = position.aroundPositions()

        actual.size shouldBe expected.size
        actual.containsAll(expected) shouldBe true
    }

    @Test
    fun `(2,2) 위치에서 8 방향 중 유효한 위치는 8개다`() {
        val x = 2
        val y = 2
        val position = Position(x = x, y = y)
        val expected = Positions(
            listOf(
                Position(x = 1, y = 1),
                Position(x = 1, y = 2),
                Position(x = 1, y = 3),
                Position(x = 2, y = 1),
                Position(x = 2, y = 3),
                Position(x = 3, y = 1),
                Position(x = 3, y = 2),
                Position(x = 3, y = 3),
            ),
        )

        val actual = position.aroundPositions()

        actual.size shouldBe expected.size
        actual.containsAll(expected) shouldBe true
    }
}
