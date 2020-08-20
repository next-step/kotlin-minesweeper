package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BoardTest {

    @ParameterizedTest
    @ValueSource(ints = [101, -1])
    fun `지뢰의 개수는 1 이상 (높이 * 너비) 이하여야 한다`(mineCount: Int) {
        assertThat(Board.isValidMineCount(10, 10, mineCount)).isFalse()
        assertThat(Board.getOrNull(10, 10, mineCount)).isNull()
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 5, 10])
    fun `사용자에게 입력받은 개수만큼 지뢰를 생성한다`(mineCount: Int) {
        val boardInfo = Board.getOrNull(10, 10, mineCount)?.boardInfo
        assertThat(boardInfo?.count { it.value.isMine() }).isEqualTo(mineCount)
    }

    @ParameterizedTest
    @ValueSource(ints = [10])
    fun `지뢰가 아닐 경우 주변 8개 사각형에 포함된 지뢰의 개수를 표시한다`(mineCount: Int) {
        val boardInfo = Board.getOrNull(10, 5, mineCount)?.boardInfo
        assertThat(boardInfo?.values).allSatisfy { it.mineCount in 0..mineCount }
    }
}
