package minesweeper_tdd.domain

import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class PositionTest {
    @ParameterizedTest
    @CsvSource(
        "0, 0",
        "3, 1",
    )
    internal fun `x, y 좌표를 가진다`(expectedX: Int, expectedY: Int) {
        val sut = Position(x = expectedX, y = expectedY)
        sut.x shouldBe expectedX
        sut.y shouldBe expectedY
    }

    @ParameterizedTest
    @CsvSource(
        "-1, -1",
        "0, -1",
        "-1, 0"
    )
    internal fun `좌표가 0보다 작으면 예외가 발생한다`(valueX: Int, valueY: Int) {
        assertThrows<IllegalArgumentException> { Position(x = valueX, y = valueY) }
    }

    @Test
    internal fun `최대 x값과 최대 y값이 주어졌을때 현재 위치에서 주변 8분면 중 탐색 가능한 위치를 추출할 수 있다`() {
        // given : (1, 0)의 위치 준비
        val sut = Position(x = 1, y = 0)

        // when : X의 최대값이 3, Y의 최대값의 3일때 (1, 0)에서 탐색 가능한 8분면의 위치 추출
        val positions = sut.nearby(maxX = 3, maxY = 3)

        // then : 추출된 위치는 다음과 같다
        // O * O
        // O O O
        // X X X
        positions.shouldContainAll(
            Position(0, 0),
            Position(2, 0),
            Position(0, 1),
            Position(1, 1),
            Position(2, 1),
        )
    }

    @Test
    internal fun `최대 x값과 최대 y값 주어졌을때 해당 범위내 올 수 있는 모든 Position 추출할 수 있다`() {
        val positions = Position.all(maxX = 2, maxY = 2)
        positions.shouldContainAll(
            Position(0, 0),
            Position(0, 1),
            Position(1, 0),
            Position(1, 1),
        )
    }
}
