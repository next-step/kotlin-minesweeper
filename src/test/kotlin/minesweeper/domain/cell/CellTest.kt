package minesweeper.domain.cell

import io.mockk.boxedClass
import minesweeper.domain.cell.Cell.Empty
import minesweeper.domain.cell.Cell.Mine
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.reflect.KClass

internal class CellTest {

    @ParameterizedTest
    @MethodSource("임의의 셀 케이스")
    fun `CLOSE 상태의 셀은 열 수 있다`(cellType: KClass<Cell>, cell: Cell) {
        // when, then
        assertThatCode { cell.open() }.doesNotThrowAnyException()
    }

    @ParameterizedTest
    @MethodSource("임의의 셀 케이스")
    fun `셀을 열어서 안에 무엇이 있는지 확인할 수 있다`(cellType: KClass<Cell>, cell: Cell) {
        // when
        val result = cell.open()

        // then
        assertThat(result).isEqualTo(cell)
        assertThat(result.boxedClass()).isEqualTo(cellType)
    }

    @ParameterizedTest
    @MethodSource("열린 상태의 셀 케이스")
    fun `OPEN 상태의 셀은 다시 열 수 없다`(cell: Cell) {
        // when
        val exceptionByCell = assertThrows<IllegalStateException> { cell.open() }

        // then
        assertThat(exceptionByCell.message).isEqualTo("cell (${cell.position.x}, ${cell.position.y}) was already opened.")
    }

    @ParameterizedTest
    @MethodSource("인접한 위치 케이스")
    fun `지뢰가 없는 인접한 셀이 모두 열리게 된다`(width: Int, height: Int, positions: Set<Position>) {
        // given
        val cells = Cells(positions.map { Empty(it) })

        // when
        cells.open(positions.first())
        val result = cells.count { it.isOpen() }

        // then
        assertThat(result).isEqualTo(positions.size)
    }

    companion object {
        private val POSITION = Position(0, 0)

        @JvmStatic
        fun `임의의 셀 케이스`() = Stream.of(
            Arguments.of(Empty::class, Empty(POSITION)),
            Arguments.of(Mine::class, Mine(POSITION))
        )

        @JvmStatic
        fun `열린 상태의 셀 케이스`() = Stream.of(
            Arguments.of(Empty(POSITION).also { it.open() }),
            Arguments.of(Mine(POSITION).also { it.open() })
        )

        @JvmStatic
        fun `인접한 위치 케이스`() = Stream.of(
            Arguments.of(
                1, 5,
                setOf(
                    Position(0, 0),
                    Position(0, 1),
                    Position(0, 2),
                    Position(0, 3),
                    Position(0, 4)
                )
            ),
            Arguments.of(
                3, 2,
                setOf(
                    Position(0, 0),
                    Position(1, 0),
                    Position(2, 0),
                    Position(0, 1),
                    Position(1, 1),
                    Position(2, 1)
                )
            )
        )
    }
}
