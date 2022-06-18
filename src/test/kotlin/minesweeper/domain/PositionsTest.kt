package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PositionsTest {
    @Test
    fun `게임 보드 사이즈 10*10 으로 100개 위치를 만든다`() {
        val positions = FixtureMineSweeper.positions10x10

        assertThat(positions.positions.size).isEqualTo(100)
    }

    @Test
    fun `100개 위치 중 10개 지뢰 위치를 만든다`() {
        val positions = FixtureMineSweeper.positions10x10

        val minePositions = positions.createRandomMinePosition(10)
        assertThat(minePositions.size).isEqualTo(10)
    }

    @Test
    fun `각 셀의 주변 셀들을 세팅한다`() {
        val allBoardPosition = FixtureMineSweeper.positions2x2

        allBoardPosition.forEach {
            it.setNearPositions(allBoardPosition)
        }

        assertThat(allBoardPosition.positions.first().nearCellPositions.size).isEqualTo(3)
    }

    @Test
    fun `게임보드의 포지션 갯수보다 지뢰가 많다면 예외 발생`() {
        val positions = FixtureMineSweeper.positions10x10

        assertThrows<IllegalArgumentException> { positions.createRandomMinePosition(101) }
    }
}
