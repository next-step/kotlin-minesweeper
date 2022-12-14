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
        (0 until height).forEach { y ->
            (0 until width).forEach { x ->
                assertThat(field.block(y, x)).isNotNull
            }
        }
    }

    @Test
    fun `Field 내 mine이 지정한 갯수만큼 생성된다`() {
        // given
        val fieldGenerator = RandomFieldGenerator()
        val width = 10
        val height = 10
        val inputMineCount = 10

        // when
        val field = fieldGenerator.generate(width, height, inputMineCount)

        // then
        var findMineCount = field.mineCount(height, width)

        assertThat(findMineCount).isEqualTo(inputMineCount)
    }

    private fun Field.mineCount(height: Int, width: Int): Int {
        var findMineCount = 0

        (0 until height).forEach { y ->
            (0 until width).forEach { x ->
                if (this.block(y, x) is Mine) {
                    findMineCount++
                }
            }
        }
        return findMineCount
    }
}
