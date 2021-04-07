package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PositionTest {

    @ParameterizedTest
    @MethodSource("existResultData")
    fun `셀이 존재하는 위치를 올바르게 찾아낸다`(row: Int, column: Int, existResult: List<Position>) {
        val width = 5
        val height = 5

        val isExists = Position.values().filter { it.isExist(Location(row, column), width, height) }

        assertThat(isExists).hasSameElementsAs(existResult)
    }

    @Test
    fun `타겟의 인덱스 값을 적절하게 찾아낸다`() {
        val index = 11
        val width = 4

        assertThat(Position.LEFT_UP.getTargetIndex(index, width)).isEqualTo(6)
        assertThat(Position.UP.getTargetIndex(index, width)).isEqualTo(7)
        assertThat(Position.RIGHT_UP.getTargetIndex(index, width)).isEqualTo(8)
        assertThat(Position.LEFT.getTargetIndex(index, width)).isEqualTo(10)
        assertThat(Position.RIGHT.getTargetIndex(index, width)).isEqualTo(12)
        assertThat(Position.LEFT_DOWN.getTargetIndex(index, width)).isEqualTo(14)
        assertThat(Position.DOWN.getTargetIndex(index, width)).isEqualTo(15)
        assertThat(Position.RIGHT_DOWN.getTargetIndex(index, width)).isEqualTo(16)
    }

    companion object {
        @JvmStatic
        private fun existResultData(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(0, 0, listOf(Position.RIGHT, Position.RIGHT_DOWN, Position.DOWN)),
                Arguments.of(
                    0, 3,
                    listOf(
                        Position.LEFT, Position.RIGHT, Position.LEFT_DOWN,
                        Position.DOWN, Position.RIGHT_DOWN
                    )
                ),
                Arguments.of(
                    3, 3,
                    listOf(
                        Position.LEFT_UP, Position.UP, Position.RIGHT_UP,
                        Position.LEFT, Position.RIGHT, Position.LEFT_DOWN, Position.DOWN, Position.RIGHT_DOWN
                    )
                ),
                Arguments.of(
                    4, 2,
                    listOf(
                        Position.LEFT_UP, Position.UP, Position.RIGHT_UP,
                        Position.LEFT, Position.RIGHT
                    )
                )
            )
        }
    }
}
