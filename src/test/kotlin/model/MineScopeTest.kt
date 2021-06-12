package model

import org.assertj.core.api.Assertions.assertThat
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

    @ParameterizedTest
    @MethodSource("positionsProvider")
    fun `getPositions 스코프 범위에 해당하는 모든 Positions 리스트 리턴`(shouldContain: List<Position>, shouldNotContain: List<Position>) {
        val mineScope = MineScope(0..2, 1..3)

        assertThat(mineScope.getPositions()).containsAll(shouldContain)
        assertThat(mineScope.getPositions()).doesNotContainSequence(shouldNotContain)
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

        @JvmStatic
        fun positionsProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        listOf(Position.get(1, 3), Position.get(2, 3), Position.get(0, 1)),
                        listOf(Position.get(0, 0))
                    )
                }
            )
        }
    }
}
