package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MinesweeperStartInfoTest {
    @Test
    fun `MinesweeperStartInfo는 지뢰 찾기를 시작하기 위한 정보인 높이, 너비, 지뢰 개수를 보관한다`() {
        val minesweeperStartInfo = MinesweeperStartInfo(
            rowCount = 10,
            columnCount = 20,
            mineCount = 30
        )

        assertAll(
            { assertThat(minesweeperStartInfo.rowCount).isEqualTo(10) },
            { assertThat(minesweeperStartInfo.columnCount).isEqualTo(20) },
            { assertThat(minesweeperStartInfo.mineCount).isEqualTo(30) }

        )
    }
}
