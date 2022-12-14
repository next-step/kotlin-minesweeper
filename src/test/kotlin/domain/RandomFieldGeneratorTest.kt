package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RandomFieldGeneratorTest {

    @Test
    fun `Field 가 지정한 크기만큼 생성된다`() {
        // given
        val fieldGenerator = RandomFieldGenerator()
        val width = 10
        val height = 10

        // when
        val field = fieldGenerator.generate(width, height, 10)

        // then
        assertThat(field.rows).hasSize(10)
        assertThat(field.rows[0].cells).hasSize(10)
    }
}
