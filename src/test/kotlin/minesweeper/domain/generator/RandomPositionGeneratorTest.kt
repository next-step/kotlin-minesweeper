package minesweeper.domain.generator

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import minesweeper.domain.BoardSize
import org.junit.jupiter.api.Test

class RandomPositionGeneratorTest {

    @Test
    fun `랜덤 포지션 생성기는 0부터 파라미터 까지의 값을 갖는 포지션을 생성한다`() {
        val boardSize = BoardSize(3, 3)
        val count = 3
        val generator = RandomPositionGenerator()

        val positions = generator.get(boardSize, count)

        assertSoftly {
            positions.size shouldBe count
            positions.all {
                it.x in 0 until boardSize.width && it.y in 0 until boardSize.height
            } shouldBe true
        }
    }
}
