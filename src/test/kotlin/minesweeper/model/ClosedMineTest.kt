package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("닫힌 지뢰 셀 테스트")
class ClosedMineTest {

    @Test
    fun `닫힌 지뢰 셀은 열림 상태가 false`() {
        // given, when, then
        assertThat(ClosedMine(CellPosition.of(0, 0)).isOpened).isFalse
    }

    @Test
    fun `닫힌 지뢰 셀은 지뢰 상태가 true`() {
        // given, when, then
        assertThat(ClosedMine(CellPosition.of(0, 0)).isMine).isTrue
    }
}
