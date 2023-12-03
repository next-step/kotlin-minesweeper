package minesweeper.domain

import io.kotest.assertions.assertSoftly
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PositionsTest {
    private val positions = setOf(
        Position(1, 1),
        Position(2, 2),
        Position(3, 3)
    ).toPositions()

    @Test
    fun `두 Positions중 중복 Position 포함 여부를 알 수 있다`() {
        // given
        val anotherPosition = setOf(
            Position(1, 1),
            Position(20, 20),
            Position(30, 30)
        ).toPositions()

        // when
        val isContainSamePosition = positions containSamePosition anotherPosition

        // then
        assertThat(isContainSamePosition).isEqualTo(true)
    }

    @Test
    fun `Positions이 주어질 때 1개 Position을 더할 수 있다`() {
        // given
        val position = Position(100, 100)

        // when
        val newPositions = positions + position

        // then
        assertSoftly {
            assertThat(newPositions.size).isEqualTo(4)
            assertThat(
                newPositions == setOf(
                    Position(1, 1),
                    Position(2, 2),
                    Position(3, 3),
                    Position(100, 100)
                ).toPositions()
            )
        }
    }

    @Test
    fun `Positions에 Position을 더할 때 중복은 제거된다`() {
        // given
        val position = Position(1, 1)

        // when
        val newPositions = positions + position

        // then
        assertSoftly {
            assertThat(newPositions.size).isEqualTo(3)
            assertThat(
                newPositions == setOf(
                    Position(1, 1),
                    Position(2, 2),
                    Position(3, 3)
                ).toPositions()
            )
        }
    }

    @Test
    fun `Positions이 주어질 때 다른 Positions를 더할 수 있다`() {
        // given
        val anotherPositions = setOf(
            Position(100, 100),
            Position(200, 200),
            Position(300, 300)
        ).toPositions()

        // when
        val newPositions = positions + anotherPositions

        // then
        assertSoftly {
            assertThat(newPositions.size).isEqualTo(6)
            assertThat(
                newPositions == setOf(
                    Position(1, 1),
                    Position(2, 2),
                    Position(3, 3),
                    Position(100, 100),
                    Position(200, 200),
                    Position(300, 300)
                ).toPositions()
            )
        }
    }

    @Test
    fun `Positions에 Positions을 더할 때 중복은 제거된다`() {
        // given
        val anotherPositions = setOf(
            Position(1, 1),
            Position(20, 20),
            Position(30, 30)
        ).toPositions()

        // when
        val newPositions = positions + anotherPositions

        // then
        assertSoftly {
            assertThat(newPositions.size).isEqualTo(5)
            assertThat(
                newPositions == setOf(
                    Position(1, 1),
                    Position(2, 2),
                    Position(3, 3),
                    Position(20, 20),
                    Position(30, 30)
                ).toPositions()
            )
        }
    }

    @Test
    fun `Positions이 주어질 때 1개 Position를 뺄 수 있다`() {
        // given
        val position = Position(1, 1)

        // when
        val newPositions = positions - position

        // then
        assertSoftly {
            assertThat(newPositions.size).isEqualTo(2)
            assertThat(
                newPositions == setOf(
                    Position(2, 2),
                    Position(3, 3)
                ).toPositions()
            )
        }
    }

    @Test
    fun `Positions이 주어질 때 다른 Positions를 뺄 수 있다`() {
        // given
        val anotherPositions = setOf(
            Position(1, 1),
            Position(2, 2),
            Position(30, 30)
        ).toPositions()

        // when
        val newPositions = positions - anotherPositions

        // then
        assertSoftly {
            assertThat(newPositions.size).isEqualTo(1)
            assertThat(newPositions == setOf(Position(3, 3)).toPositions())
        }
    }
}
