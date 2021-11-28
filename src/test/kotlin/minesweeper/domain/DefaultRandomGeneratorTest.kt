package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class DefaultRandomGeneratorTest {

    @Test
    fun `시작값부터 열린 구간으로 count만큼의 임의의 번호를 생성한다`() {
        val result = DefaultRandomGenerator.generate(start = 0, until = 3, count = 3)

        assertAll(
            { assertThat(result).containsExactlyInAnyOrder(0, 1, 2) },
            { assertThat(result).doesNotContain(3) },
        )
    }
}
