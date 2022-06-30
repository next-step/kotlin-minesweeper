package minesweeper.domain.cell

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class PositionTest {

    @ParameterizedTest
    @MethodSource("대상 Position 과 인접한 Position 목록")
    fun `인접한 Position 들을 구할 수 있다`(position: Position, expected: Set<Position>) {
        // when
        val nearbyPosition = position.getNearbyPosition()

        // then
        assertThat(nearbyPosition).containsExactlyInAnyOrderElementsOf(expected)
    }

    companion object {
        @JvmStatic
        fun `대상 Position 과 인접한 Position 목록`() = Stream.of(
            Arguments.of(
                Position(0, 0),
                setOf(
                    Position(-1, -1),
                    Position(-1, 0),
                    Position(-1, 1),
                    Position(0, -1),
                    Position(0, 1),
                    Position(1, -1),
                    Position(1, 0),
                    Position(1, 1)
                )
            ),
            Arguments.of(
                Position(10, 9),
                setOf(
                    Position(9, 8),
                    Position(9, 9),
                    Position(9, 10),
                    Position(10, 8),
                    Position(10, 10),
                    Position(11, 8),
                    Position(11, 9),
                    Position(11, 10)
                )
            ),
            Arguments.of(
                Position(9, 10),
                setOf(
                    Position(8, 9),
                    Position(8, 10),
                    Position(8, 11),
                    Position(9, 9),
                    Position(9, 11),
                    Position(10, 9),
                    Position(10, 10),
                    Position(10, 11)
                )
            )
        )
    }
}
