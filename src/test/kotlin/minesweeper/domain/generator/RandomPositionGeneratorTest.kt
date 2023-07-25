package minesweeper.domain.generator

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class RandomPositionGeneratorTest {

    @Test
    fun `랜덤 포지션 생성기는 0부터 파라미터 까지의 값을 갖는 포지션을 생성한다`() {
        val maxWidth = 10
        val maxHeight = 10
        val count = 10
        val generator = RandomPositionGenerator()

        val positions = generator.get(maxWidth, maxHeight, count)

        assertSoftly {
            positions.size shouldBe count
            positions.all {
                it.x in 0 until maxWidth && it.y in 0 until maxHeight
            } shouldBe true
        }
    }
}
