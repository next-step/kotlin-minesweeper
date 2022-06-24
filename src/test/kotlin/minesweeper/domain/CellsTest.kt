package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CellsTest {
    @Test
    fun `게임판(10*10)에 꽂을 셀과 지뢰 5개를 생성한다`() {
        val positions = Positions(FixtureMineSweeper.positions10x10)
        val minePositions = positions.createRandomMinePosition(5)
        val cells = Cells.of(positions, minePositions)

        assertThat(cells.cells.size).isEqualTo(100)
        assertThat(cells.cells.count { it.cellState.cellType == CellType.MINE }).isEqualTo(5)
        assertThat(cells.cells.count { it.cellState.cellType == CellType.NON_MINE }).isEqualTo(95)
    }

    @CsvSource(value = ["0,0", "0,1", "0,2", "0,3", "0,4"], delimiter = ',')
    @ParameterizedTest
    fun `게임판(5*5)에 꽂을 셀과 지뢰 5개 중 하나를 선택한 경우 상태는 bomb`(x: Int, y: Int) {
        val mockPositions = MockPositions(FixtureMineSweeper.mockPositions5x5)
        val positions = Positions(mockPositions)
        val minePositions = mockPositions.createRandomMinePosition(5)
        val cells = Cells.of(positions, minePositions)

        cells.click(Position(x, y))

        assertThat(cells.state()).isEqualTo(BoardState.BOMB)
    }

    @CsvSource(value = ["1,0", "1,1", "1,2", "1,3", "1,4"], delimiter = ',')
    @ParameterizedTest
    fun `게임판(5*5)에 꽂을 셀과 지뢰 1개 중 지뢰를 선택하지 않은경우 continue`(x: Int, y: Int) {
        val mockPositions = MockPositions(FixtureMineSweeper.mockPositions5x5)
        val positions = Positions(mockPositions)
        val minePositions = mockPositions.createRandomMinePosition(5)
        val cells = Cells.of(positions, minePositions)

        cells.click(Position(x, y))

        assertThat(cells.state()).isEqualTo(BoardState.CONTINUE)
    }

    @Test
    fun `게임판(2*2)에 꽂을 셀과 지뢰 1개 중 지뢰를 모두 피한경우 win`() {
        val mockPositions = MockPositions(FixtureMineSweeper.mockPositions2x2)
        val positions = Positions(mockPositions)
        val minePositions = mockPositions.createRandomMinePosition(1)
        val cells = Cells.of(positions, minePositions)

        cells.click(Position(1, 0))
        cells.click(Position(1, 1))
        cells.click(Position(0, 1))

        assertThat(cells.count { it.isOpen() }).isEqualTo(3)
        assertThat(cells.state()).isEqualTo(BoardState.WIN)
    }

    @Test
    fun `게임판(5*5)에 꽂을 셀과 지뢰 1개 중 하나의 셀을 선택한 경우 open`() {
        val mockPositions = MockPositions(FixtureMineSweeper.mockPositions5x5)
        val positions = Positions(mockPositions)
        val minePositions = mockPositions.createRandomMinePosition(6)
        val cells = Cells.of(positions, minePositions)

        cells.click(Position(2, 0))
        assertThat(cells.count { it.isOpen() }).isEqualTo(1)
        cells.click(Position(1, 1))
        assertThat(cells.count { it.isOpen() }).isEqualTo(2)
        cells.click(Position(2, 1))
        assertThat(cells.count { it.isOpen() }).isEqualTo(3)
        cells.click(Position(2, 2))
        assertThat(cells.count { it.isOpen() }).isEqualTo(19)
        assertThat(cells.state()).isEqualTo(BoardState.WIN)
    }

    @Test
    fun `게임판(5*5)에 5개의 마인 주변 셀을 열었을때 오픈되는 주변 셀의 갯수`() {
        val mockPositions = MockPositions(FixtureMineSweeper.mockPositions5x5)
        val positions = Positions(mockPositions)
        val minePositions = FixtureMineSweeper.customMinePosition5x5
        val cells = Cells.of(positions, minePositions)

        cells.click(Position(0, 1))
        assertThat(cells.count { it.isOpen() }).isEqualTo(1)
        cells.click(Position(0, 4))
        assertThat(cells.count { it.isOpen() }).isEqualTo(9)
        cells.click(Position(4, 0))
        assertThat(cells.count { it.isOpen() }).isEqualTo(17)
    }

    @Test
    fun `이미 클릭 된 포지션을 입력하는 경우 중복 에러 발생`() {
        val mockPositions = MockPositions(FixtureMineSweeper.mockPositions2x2)
        val positions = Positions(mockPositions)
        val minePositions = mockPositions.createRandomMinePosition(1)
        val cells = Cells.of(positions, minePositions)

        cells.click(Position(1, 0))

        assertThrows<IllegalArgumentException> { assertThat(cells.click(Position(1, 0))) }
    }

    @Test
    fun `유효하지 않는 포지션을 입력하는 경우 중복 에러 발생`() {
        val mockPositions = MockPositions(FixtureMineSweeper.mockPositions2x2)
        val positions = Positions(mockPositions)
        val minePositions = mockPositions.createRandomMinePosition(1)
        val cells = Cells.of(positions, minePositions)

        assertThrows<IllegalArgumentException> { assertThat(cells.click(Position(3, 0))) }
    }
}
