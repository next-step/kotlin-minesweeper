package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MineTest {
    @DisplayName("symbol이 *이면 true를 리턴한다.")
    @Test
    fun isMine() {
        val mine = Mine()
        assertThat(mine.isMine()).isTrue()
    }

    @DisplayName("symbol이 *이 아니면 false를 리턴한다.")
    @Test
    fun isNotMine() {
        val mine = SafetyBlock()
        assertThat(mine.isMine()).isFalse()
    }
}
