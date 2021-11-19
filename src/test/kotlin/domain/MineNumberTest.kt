package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MineNumberTest {

    @DisplayName("MineNumber 의 초기값은 0 이다.")
    @Test
    fun default() {
        assertThat(MineNumber().number).isZero
    }

    @DisplayName("MineNumber 를 ++ 하면 증가한다.")
    @Test
    fun inc() {
        var mineNumber = MineNumber()
        mineNumber++
        assertThat(mineNumber.number).isEqualTo(1)
    }
}
