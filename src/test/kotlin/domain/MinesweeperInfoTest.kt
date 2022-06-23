package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MinesweeperInfoTest {
    @Test
    fun `MinesweeperStartInfo는 지뢰 찾기를 시작하기 위한 정보인 높이, 너비, 지뢰 개수를 보관한다`() {
        val minesweeperInfo = MinesweeperInfo(
            rowCount = 10,
            columnCount = 20,
            mineCount = 30
        )

        assertAll(
            { assertThat(minesweeperInfo.rowCount).isEqualTo(10) },
            { assertThat(minesweeperInfo.columnCount).isEqualTo(20) },
            { assertThat(minesweeperInfo.mineCount).isEqualTo(30) }
        )
    }
}
