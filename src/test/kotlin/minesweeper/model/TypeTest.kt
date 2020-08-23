package minesweeper.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TypeTest {
    @Test
    fun `Type from Test`() {
        Assertions.assertThat(Type.from(5).symbol).isEqualTo("5")
        Assertions.assertThat(Type.from(-1).symbol).isEqualTo("*")
    }
}
