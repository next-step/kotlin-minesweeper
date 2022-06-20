package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class Ints {

    @Test
    fun `coerceAtMost 학습테스트`() {

        assertAll(
            { assertThat(100.coerceAtMost(10)).isEqualTo(10) },
            { assertThat(5.coerceAtMost(10)).isEqualTo(5) },
            { assertThat(5.coerceAtMost(-5)).isEqualTo(-5) }
        )
    }

    @Test
    fun `coerceIn 학습테스트`() {

        assertAll(
            { assertThat(100.coerceIn(1, 10)).isEqualTo(10) },
            { assertThat(5.coerceIn(10, 20)).isEqualTo(10) },
            { assertThat(5.coerceIn(0, 5)).isEqualTo(5) },
            { assertThat(5.coerceIn(5, 15)).isEqualTo(5) },
            { assertThat(8.coerceIn(5, 15)).isEqualTo(8) }
        )
    }
}
