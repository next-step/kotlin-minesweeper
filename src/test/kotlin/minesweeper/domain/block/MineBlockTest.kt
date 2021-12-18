package minesweeper.domain.block

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("지뢰(MineBlock)")
internal class MineBlockTest {

    @Test
    fun `지뢰다`() {
        val mineBlock = MineBlock()

        assertThat(mineBlock.isMine).isTrue
    }

    @Test
    fun `안열린 상태 여부를 반환한다`() {
        val mineBlock = MineBlock()

        assertThat(mineBlock.isOpened()).isFalse
    }

    @Test
    fun `열린 상태 여부를 반환한다`() {
        val mineBlock = MineBlock()
        val actual = mineBlock.open()

        assertThat(actual.isOpened()).isTrue
    }
}
