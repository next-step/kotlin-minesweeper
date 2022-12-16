package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MineFieldFactoryTest {
    @Test
    fun `지뢰 수는 직사각형의 크기와 지뢰 수에 따른 지뢰판을 만든다`() {
        val rectangle = Rectangle(5, 5)
        val mineCount = 5
        val mineField = MineFieldFactory().generate(rectangle, mineCount)
        mineField.blocks.size shouldBe 25
        mineField.blocks.count { it.isMine() } shouldBe mineCount
    }
}
