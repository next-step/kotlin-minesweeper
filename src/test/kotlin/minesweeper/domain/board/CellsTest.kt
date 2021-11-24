package minesweeper.domain.board

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CellsTest {

    @Test
    fun `100개의 셀에 3개의 지뢰를 넣었을 경우 위치가 정확한지 테스트`() {
        // given
        val positions = Positions.of(BoardSize.of(10, 10))
        val minePositions = Positions(listOf(Position(1, 1), Position(1, 2), Position(1, 3)))
        val cells = Cells.of(positions, minePositions)

        // then
        assertAll({
            assertThat(cells[0].position).isEqualTo(minePositions[0])
            assertThat(cells[1].position).isEqualTo(minePositions[1])
            assertThat(cells[2].position).isEqualTo(minePositions[2])
        })
    }

    @Test
    fun `셀에서 지뢰가 아닌 경우에는 값은 0이다`() {
        // given
        val positions = Positions.of(BoardSize.of(10, 10))
        val minePositions = Positions(listOf(Position(1, 1), Position(1, 2), Position(1, 3)))
        val cells = Cells.of(positions, minePositions)

        // then
        assertAll({
            assertThat(cells[4].cellType.getValue()).isEqualTo(0)
        })
    }

    @Test
    fun `셀에서 지뢰인 경우 값은 -1이다`() {
        // given
        val positions = Positions.of(BoardSize.of(10, 10))
        val minePositions = Positions(listOf(Position(1, 1), Position(1, 2), Position(1, 3)))
        val cells = Cells.of(positions, minePositions)

        // then
        assertAll({
            assertThat(cells[1].cellType.getValue()).isEqualTo(-1)
        })
    }
}
