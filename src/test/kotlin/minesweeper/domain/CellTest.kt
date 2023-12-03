package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellTest {
    @Test
    fun `Empty cell이 open될 경우 OpenState은 OPENED로 변경된다`() {
        // given
        val empty = Empty()
        // when
        val newEmpty = empty.open()
        // then
        assertThat(newEmpty.openState).isEqualTo(OpenState.OPENED)
    }

    @Test
    fun `Mine cell이 open될 경우 OpenState은 OPENED로 변경된다`() {
        // given
        val mine = Mine()
        // when
        val newMine = mine.open()
        // then
        assertThat(newMine.openState).isEqualTo(OpenState.OPENED)
    }
}
