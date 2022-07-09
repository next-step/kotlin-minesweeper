package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RandomMinePositionsFactoryTest {
    @Test
    fun `RandomMinePositionsFactory는 MinesweeperInfo를 받아 무작위로 생성된 지뢰 좌표 목록을 생성한다`() {
        val minesweeperInfo = MinesweeperInfo(
            rowCount = 10,
            columnCount = 15,
            mineCount = 5
        )
        val minePositions = RandomMinePositionsFactory.create(minesweeperInfo)

        assertThat(minePositions).hasSize(minesweeperInfo.mineCount)
    }
}
