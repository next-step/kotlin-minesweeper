package minsweeper.domain.generate

import minsweeper.domain.board.BoardSize
import minsweeper.domain.Cell
import minsweeper.domain.Coordinate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class CellGeneratorTest {

    @ParameterizedTest
    @MethodSource("provideCellGeneratorMineTestParam")
    fun `생성하려는 Cell이 지뢰 Cell에 포함되어 있으면 지뢰 Cell을 생성해야 한다`(param: CellGeneratorTestParam) {
        // given
        val generator = CellGenerator()
        val boardSize = BoardSize(10, 10)

        // when
        val result = generator.generate(param.coordinate, param.mineCoordinates, boardSize)

        // then
        assertThat(result).isInstanceOf(Cell.Mine::class.java)
    }

    @ParameterizedTest
    @MethodSource("provideCellGeneratorIslandTestParam")
    fun `생성하려는 Cell이 일반이면 일반 Cell을 생성해야 한다`(param: CellGeneratorTestParam) {
        // given
        val generator = CellGenerator()
        val boardSize = BoardSize(10, 10)

        // when
        val result = generator.generate(param.coordinate, param.mineCoordinates, boardSize)

        // then
        assertThat(result).isInstanceOf(Cell.Island::class.java)
    }

    companion object {

        @JvmStatic
        fun provideCellGeneratorMineTestParam(): List<CellGeneratorTestParam> = listOf(
            CellGeneratorTestParam(
                Coordinate.of(10, 10),
                listOf(Coordinate.of(10, 10), Coordinate.of(9, 9)),
            ),
            CellGeneratorTestParam(
                Coordinate.of(1, 1),
                listOf(Coordinate.of(1, 1), Coordinate.of(9, 9)),
            ),
        )

        @JvmStatic
        fun provideCellGeneratorIslandTestParam(): List<CellGeneratorTestParam> = listOf(
            CellGeneratorTestParam(
                Coordinate.of(10, 10),
                listOf(Coordinate.of(9, 9), Coordinate.of(9, 10)),
            ),
            CellGeneratorTestParam(
                Coordinate.of(1, 1),
                listOf(Coordinate.of(2, 2), Coordinate.of(9, 9)),
            ),
        )

    }

    data class CellGeneratorTestParam(
        val coordinate: Coordinate,
        val mineCoordinates: List<Coordinate>,
    )

}