package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PositionsTest {
    @Test
    fun `게임 보드 사이즈 10*10 으로 100개 위치를 만든다`() {
        val gameBoardSize = GameBoardSize(10, 10)
        val positions = Positions.of(gameBoardSize)

        assertThat(positions.positions.size).isEqualTo(100)
    }

    @Test
    fun `100개 위치 중 10개 지뢰 위치를 만든다`() {
        val gameBoardSize = GameBoardSize(10, 10)
        val positions = Positions.of(gameBoardSize)

        val minePositions = positions.createRandomMinePosition(10)
        assertThat(minePositions.size).isEqualTo(10)
    }
}
