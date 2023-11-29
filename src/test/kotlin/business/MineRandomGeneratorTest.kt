package business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MineRandomGeneratorTest {
    @Test
    fun `count 만큼 mines를 생성한다`() {
        // given
        val height = 2
        val width = 2
        val count = 1

        // when
        val mines = MineRandomPointGenerator().generate(BoardInfo(height, width, count))

        // when
        mines.size shouldBe 1
    }
}
