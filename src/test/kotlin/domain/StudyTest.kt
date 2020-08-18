package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StudyTest {

    @Test
    fun `Pair는 순서를 따진다`() {
        // given
        val pair1 = 1 to 0
        val pair2 = 0 to 1
        val pair3 = 0 to 1

        // then
        assertThat(pair1 == pair2).isFalse()
        assertThat(pair2 == pair3).isTrue()
    }
}
