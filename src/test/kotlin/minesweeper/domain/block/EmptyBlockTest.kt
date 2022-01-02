package minesweeper.domain.block

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("일반 블록(EmptyBlock)")
internal class EmptyBlockTest {

    @Test
    fun `지뢰가 아니다`() {
        val emptyBlock = EmptyBlock()

        assertThat(emptyBlock.isMine).isFalse
    }

    @Test
    fun `안열린 상태 여부를 반환한다`() {
        val emptyBlock = EmptyBlock()

        assertThat(emptyBlock.isOpened()).isFalse
    }

    @Test
    fun `열린 상태 여부를 반환한다`() {
        val emptyBlock = EmptyBlock()
        val actual = emptyBlock.open()

        assertThat(actual.isOpened()).isTrue
    }
}
