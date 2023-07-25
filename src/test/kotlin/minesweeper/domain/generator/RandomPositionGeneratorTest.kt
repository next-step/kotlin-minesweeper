package minesweeper.domain.generator

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import minesweeper.domain.BoardMeta
import org.junit.jupiter.api.Test

class RandomPositionGeneratorTest {

    @Test
    fun `랜덤 포지션 생성기는 0부터 파라미터 까지의 값을 갖는 포지션을 생성한다`() {
        val boardMeta = BoardMeta(3, 3, 3)
        val generator = RandomPositionGenerator()

        val positions = generator.get(boardMeta)

        assertSoftly {
            positions.size shouldBe boardMeta.mineCount
            positions.all {
                it.x in 0 until boardMeta.width && it.y in 0 until boardMeta.height
            } shouldBe true
        }
    }
}
