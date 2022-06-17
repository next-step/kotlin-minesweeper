package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PositionsTest {
    @Test
    fun `게임 보드 사이즈 10*10 으로 100개 위치를 만든다`() {
        val positions = Positions(GameBoardSize(10, 10).createPositions())

        assertThat(positions.positions.size).isEqualTo(100)
    }

    @Test
    fun `100개 위치 중 10개 지뢰 위치를 만든다`() {
        val positions = Positions(GameBoardSize(10, 10).createPositions())

        val minePositions = positions.createRandomMinePosition(10)
        assertThat(minePositions.size).isEqualTo(10)
    }

    @Test
    fun `각 셀의 주변 셀들을 세팅한다`() {
        val allBoardPosition = Positions(
            listOf(
                Position(0, 0),
                Position(0, 1),
                Position(1, 0),
                Position(1, 1)
            )
        )

        allBoardPosition.forEach {
            it.setNearPositions(allBoardPosition)
        }

        assertThat(allBoardPosition.positions.first().nearCellPositions.size).isEqualTo(3)
    }
}
