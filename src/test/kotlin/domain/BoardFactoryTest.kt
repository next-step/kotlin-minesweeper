package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

class BoardFactoryTest {
    @Test
    fun `가로 5, 세로 5 크기의 지뢰판은 총 25개의 블록을 가진다`() {
        val rectangle = Rectangle(Width(5), Height(5))
        val mineCount = MineCount(5)
        val board = BoardFactory().generate(rectangle, mineCount)
        board.getBlocks().size shouldBe 25
        board.getBlocks().count { it.value.isMine() } shouldBe mineCount.value
    }

    @RepeatedTest(10)
    fun `보드 생성시 각 블록은 주변 지뢰 수를 계산하여 가지고 생성된다`() {
        val rectangle = Rectangle(Width(2), Height(2))
        val mineCount = MineCount(1)
        val board = BoardFactory().generate(rectangle, mineCount)
        val block = board.getBlockByPosition(Position.of(0, 0))
        require(block != null)
        if (block.isMine()) {
            block.getMineCount() shouldBe 0
        } else {
            block.getMineCount() shouldBe 1
        }
    }
}
