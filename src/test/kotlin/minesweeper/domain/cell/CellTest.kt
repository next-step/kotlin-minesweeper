package minesweeper.domain.cell

import io.mockk.boxedClass
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.Test
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
        assertThat(exceptionByCell.message).isEqualTo("cell was already opened.")
    }

    @Test
    fun `지뢰가 없는 인접한 셀이 모두 열리게 된다`() {
        TODO()
    }

    companion object {
        val POSITION = Position(0, 0, 0)
        val NEARBY_POSITIONS = Positions.from(emptyList())

        @JvmStatic
        fun `임의의 셀 케이스`() = Stream.of(
            Arguments.of(Empty::class, Empty(POSITION, NEARBY_POSITIONS)),
            Arguments.of(Mine::class, Mine(POSITION, NEARBY_POSITIONS))
        )

        @JvmStatic
        fun `열린 상태의 셀 케이스`() = Stream.of(
            Arguments.of(Empty(POSITION, NEARBY_POSITIONS).also { it.open() }),
            Arguments.of(Mine(POSITION, NEARBY_POSITIONS).also { it.open() })
        )
    }
}
