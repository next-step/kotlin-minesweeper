package minesweeper.domain

import io.kotest.matchers.maps.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import minesweeper.cellsOf
import minesweeper.closedEmptyCellOf
import minesweeper.openedEmptyCellOf
import minesweeper.undetonatedMineCellOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@Suppress("NonAsciiCharacters")
class CellsTest {
    @Test
    fun `빈 Map 으로 생성할 수 없다`() {
        assertThrows<IllegalArgumentException> { Cells(emptyMap()) }
    }

    @Test
    fun `코너 칸들 주변의 지뢰 수를 센다`() {
        val cells =
            cellsOf(
                openedEmptyCellOf(y = 0, x = 0),
                undetonatedMineCellOf(y = 0, x = 1),
                openedEmptyCellOf(y = 0, x = 2),
                undetonatedMineCellOf(y = 1, x = 0),
                closedEmptyCellOf(y = 1, x = 1),
                undetonatedMineCellOf(y = 1, x = 2),
                openedEmptyCellOf(y = 2, x = 0),
                undetonatedMineCellOf(y = 2, x = 1),
                openedEmptyCellOf(y = 2, x = 2),
            )
//        2 * 2
//        * C *
//        2 * 2

        cells.countNeighboringMines(Coordinate(y = 0, x = 0)) shouldBe 2
        cells.countNeighboringMines(Coordinate(y = 2, x = 0)) shouldBe 2
        cells.countNeighboringMines(Coordinate(y = 2, x = 2)) shouldBe 2
        cells.countNeighboringMines(Coordinate(y = 0, x = 2)) shouldBe 2
    }

    @Test
    fun `가운데 있는 칸 주변의 지뢰 수를 센다`() {
        val cells =
            cellsOf(
                closedEmptyCellOf(y = 0, x = 0),
                undetonatedMineCellOf(y = 0, x = 1),
                closedEmptyCellOf(y = 0, x = 2),
                undetonatedMineCellOf(y = 1, x = 0),
                openedEmptyCellOf(y = 1, x = 1),
                undetonatedMineCellOf(y = 1, x = 2),
                closedEmptyCellOf(y = 2, x = 0),
                undetonatedMineCellOf(y = 2, x = 1),
                closedEmptyCellOf(y = 2, x = 2),
            )
//        C * C
//        * 4 *
//        C * C

        val count = cells.countNeighboringMines(Coordinate(y = 1, x = 1))

        count shouldBe 4
    }

    @Test
    fun `열려고 의도하는 좌표에 칸이 없으면 예외를 던진다`() {
        val cells = Cells(mapOf(closedEmptyCellOf(y = 0, x = 0)))
        assertThrows<IllegalArgumentException> { cells.open(Coordinate(y = 99, x = 99)) }
    }

    @Test
    fun `이미 열린 칸을 열 수 없다`() {
        val cells = Cells(mapOf(openedEmptyCellOf(y = 0, x = 0)))
        assertThrows<IllegalStateException> { cells.open(Coordinate(y = 0, x = 0)) }
    }

    @Test
    fun `지뢰가 있는 칸을 열면 지뢰가 폭파되고 지뢰가 폭파된 보드가 된다`() {
        var cells = Cells(mapOf(undetonatedMineCellOf(y = 0, x = 0)))

        cells = cells.open(Coordinate(y = 0, x = 0))

        cells[Coordinate(y = 0, x = 0)].shouldBeTypeOf<DetonatedMineCell>()
    }

    @Test
    fun `칸에 주변에 지뢰가 있는 경우 해당 칸만 열린다`() {
        var cells =
            cellsOf(
                closedEmptyCellOf(y = 0, x = 0),
                closedEmptyCellOf(y = 0, x = 1),
                undetonatedMineCellOf(y = 1, x = 0),
                undetonatedMineCellOf(y = 1, x = 1),
            )
//        C C
//        * *

        cells = cells.open(Coordinate(y = 0, x = 0))

        cells shouldContainExactly
            mapOf(
                openedEmptyCellOf(y = 0, x = 0),
                closedEmptyCellOf(y = 0, x = 1),
                undetonatedMineCellOf(y = 1, x = 0),
                undetonatedMineCellOf(y = 1, x = 1),
            )
//        2 C
//        * *
    }

    @Test
    fun `지뢰와 인접하지 않은 주변의 모든 칸이 열린다`() {
        var cells =
            cellsOf(
                closedEmptyCellOf(y = 0, x = 0),
                closedEmptyCellOf(y = 0, x = 1),
                undetonatedMineCellOf(y = 0, x = 2),
                closedEmptyCellOf(y = 1, x = 0),
                closedEmptyCellOf(y = 1, x = 1),
                closedEmptyCellOf(y = 1, x = 2),
                closedEmptyCellOf(y = 2, x = 0),
                closedEmptyCellOf(y = 2, x = 1),
                undetonatedMineCellOf(y = 2, x = 2),
            )
//        C C *
//        C C C
//        C C *

        cells = cells.open(Coordinate(y = 0, x = 0))

        cells shouldContainExactly
            mapOf(
                openedEmptyCellOf(y = 0, x = 0),
                openedEmptyCellOf(y = 0, x = 1),
                undetonatedMineCellOf(y = 0, x = 2),
                openedEmptyCellOf(y = 1, x = 0),
                openedEmptyCellOf(y = 1, x = 1),
                closedEmptyCellOf(y = 1, x = 2),
                openedEmptyCellOf(y = 2, x = 0),
                openedEmptyCellOf(y = 2, x = 1),
                undetonatedMineCellOf(y = 2, x = 2),
            )
//        0 1 *
//        1 1 C
//        1 1 *
    }

    @ParameterizedTest
    @MethodSource
    fun `닫혀 있는 빈 칸이 있는지 리턴한다`(
        cells: Cells,
        expected: Boolean,
    ) {
        cells.isAnyEmptyClosed() shouldBe expected
    }

    companion object {
        @JvmStatic
        fun `닫혀 있는 빈 칸이 있는지 리턴한다`() =
            listOf(
                Arguments.of(
                    cellsOf(
                        closedEmptyCellOf(y = 0, x = 0),
                        openedEmptyCellOf(y = 0, x = 1),
                        undetonatedMineCellOf(y = 1, x = 0),
                        undetonatedMineCellOf(y = 1, x = 1),
                    ),
//                    C 2
//                    * *
                    true,
                ),
                Arguments.of(
                    cellsOf(
                        openedEmptyCellOf(y = 0, x = 0),
                        openedEmptyCellOf(y = 0, x = 1),
                        undetonatedMineCellOf(y = 1, x = 0),
                        undetonatedMineCellOf(y = 1, x = 1),
                    ),
//                    2 2
//                    * *
                    false,
                ),
            )
    }
}
