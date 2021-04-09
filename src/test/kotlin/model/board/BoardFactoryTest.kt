package model.board

import model.Position
import model.Positions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class BoardFactoryTest {
    private val sut = BoardFactory()

    @ParameterizedTest
    @MethodSource("createConditionProvider")
    fun `인자로 들어온 값들에 알맞은 Board 생성`(boardSize: BoardSize, positions: Positions) {
        val board = sut.create(boardSize, positions)

        assertThat(board.height).isEqualTo(boardSize.height)
        assertThat(board.width).isEqualTo(boardSize.width)
        positions.forEach {
            assertThat(board.getCell(it).contents).isEqualTo(Contents.MINE)
        }
    }

    companion object {
        @JvmStatic
        private fun createConditionProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        BoardSize(5, 4),
                        Positions(
                            Position.get(1, 2),
                            Position.get(3, 3)
                        )
                    )
                }
            )
        }
    }
}
