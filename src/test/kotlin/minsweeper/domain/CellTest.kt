package minsweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellTest {

    @Test
    fun `셀을 오픈할 수 있다`() {
        // given
        val cell = Cell.Island(aroundMineAmount = 1)

        // when
        cell.open()

        // then
        assertThat(cell.isOpened).isTrue()
    }

    @Test
    fun `셀이 지뢰이면 isMine은 참이어야 한다`() {
        // given
        val cell = Cell.Mine()

        // when
        val result = cell.isMine()

        // then
        assertThat(result).isTrue()
    }

    @Test
    fun `셀이 일반 땅이면 isMine은 거짓이어야 한다`() {
        // given
        val cell = Cell.Island(aroundMineAmount = 1)

        // when
        val result = cell.isMine()

        // then
        assertThat(result).isFalse()
    }

}