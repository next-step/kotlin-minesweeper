package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class MineScopeTest {
    @ParameterizedTest
    @MethodSource("mineScopeProvider")
    fun `주어진 board 사이즈와 position 에 맞는 위치`(position: Position, boardHeight: Int, boardWidth: Int, shouldEqual: MineScope) {
        val result = MineScope(position, boardHeight, boardWidth)

        assertThat(result).isEqualTo(shouldEqual)
    }

    @Test
    fun `getPositions 스코프 범위에 해당하는 모든 Positions 리스트 리턴`() {
        val mineScope = MineScope(0..2, 1..3)

        assertThat(mineScope.getPositions()).contains(Position.get(1, 3), Position.get(2, 3), Position.get(0, 1))
        assertThat(mineScope.getPositions()).doesNotContain(Position.get(0, 0))
    }

    companion object {
        @JvmStatic
        fun mineScopeProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        Position.get(0, 0),
                        5,
                        5,
                        MineScope(0..1, 0..1)
                    )
                },
                Arguments {
                    arrayOf(
                        Position.get(1, 1),
                        5,
                        5,
                        MineScope(0..2, 0..2)
                    )
                },
                Arguments {
                    arrayOf(
                        Position.get(1, 4),
                        5,
                        5,
                        MineScope(0..2, 3..4)
                    )
                }
            )
        }
    }
}
