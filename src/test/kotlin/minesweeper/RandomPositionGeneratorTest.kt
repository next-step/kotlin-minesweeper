package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RandomPositionGeneratorTest {

    @Test
    fun `랜덤_숫자를_생성한다`() {
        val generator = RandomPositionGenerator()

        val randomNumber = generator.generate(5)

        assertThat(randomNumber).isGreaterThan(0)
        assertThat(randomNumber).isLessThan(5)
    }
}