package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BoardFactoryTest {
    @Test
    fun `가로 5, 세로 5 크기의 지뢰판은 총 25개의 블록을 가진다`() {
        val rectangle = Rectangle(Width(5), Height(5))
        val mineCount = MineCount(5)
        val board = BoardFactory().generate(rectangle, mineCount)
        board.getBlocks().size shouldBe 25
        board.getBlocks().count { it.isMine() } shouldBe mineCount.value
    }
}
