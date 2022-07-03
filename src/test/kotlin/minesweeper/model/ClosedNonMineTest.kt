package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("닫혀있고 지뢰가 아닌 셀 테스트")
class ClosedNonMineTest {

    @Test
    fun `닫혀있고 지뢰가 아닌 셀은 열림 상태가 false`() {
        // given, when, then
        assertThat(ClosedNonMine(CellPosition.of(0, 0)).isOpened).isFalse
    }

    @Test
    fun `닫혀있고 지뢰가 아닌 셀은 지뢰 상태가 false`() {
        // given, when, then
        assertThat(ClosedNonMine(CellPosition.of(0, 0)).isMine).isFalse
    }
}
