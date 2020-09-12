package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SafetyBlockTest {
    @DisplayName("mineCount의 갯수에 1을 더한다.")
    @Test
    fun setMineCount() {
        val safetyBlock = SafetyBlock()
        safetyBlock.setMineCount()
        assertThat(safetyBlock.symbol).isEqualTo("1")
    }
}
