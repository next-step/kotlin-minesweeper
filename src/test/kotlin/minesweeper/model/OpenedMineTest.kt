package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("열려있고 지뢰인 셀 테스트")
class OpenedMineTest {
    @Test
    fun `열려있고 지뢰인 셀은 열림 상태가 true`() {
        // given, when, then
        assertThat(OpenedMine(CellPosition.of(0, 0)).isOpened).isTrue
    }

    @Test
    fun `열려있고 지뢰인 셀은 지뢰 상태가 true`() {
        // given, when, then
        assertThat(OpenedMine(CellPosition.of(0, 0)).isMine).isTrue
    }
}
