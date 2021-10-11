package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class StateTest {
    @Test
    fun `open 과 close 두개의 state 가 존재한다`() {
        assertThat(State.values()).containsExactly(State.OPEN, State.CLOSE)
    }
}