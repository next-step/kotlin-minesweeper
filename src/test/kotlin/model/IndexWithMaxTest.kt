package model

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class IndexWithMaxTest {
    @Test
    fun `index 가 음수이면 IllegalArgumentException 던진다`() {
        Assertions.assertThatThrownBy {
            IndexWithMax(-1, 1)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `index 가 max 초과이면 IllegalArgumentException 던진다`() {
        IndexWithMax(2, 2)
        Assertions.assertThatThrownBy {
            IndexWithMax(3, 1)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @ParameterizedTest
    @MethodSource("aroundIndexesWithMaxProvider")
    fun `aroundIndexesWithMax`(indexWithMax: IndexWithMax, expected: List<IndexWithMax>) {
        assertThat(indexWithMax.aroundIndexesWithMax()).containsExactlyInAnyOrderElementsOf(expected)
    }

    companion object {
        @JvmStatic
        fun aroundIndexesWithMaxProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        IndexWithMax(1, 2),
                        listOf(
                            IndexWithMax(0, 2),
                            IndexWithMax(1, 2),
                            IndexWithMax(2, 2)
                        )
                    )
                },
                Arguments {
                    arrayOf(
                        IndexWithMax(0, 2),
                        listOf(
                            IndexWithMax(0, 2),
                            IndexWithMax(1, 2)
                        )
                    )
                },
                Arguments {
                    arrayOf(
                        IndexWithMax(2, 2),
                        listOf(
                            IndexWithMax(1, 2),
                            IndexWithMax(2, 2)
                        )
                    )
                }
            )
        }
    }
}