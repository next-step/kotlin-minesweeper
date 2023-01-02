package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun `보드의 position에 해당하는 블록을 가져온다`() {
        val selectPosition = Position.of(0, 2)
        val rectangle = Rectangle(Width(3), Height(3))
        val blocks = fakeBlocks(positions(3, 3), selectPosition)
        val board = Board(rectangle, blocks)
        board.getBlockByPosition(selectPosition)?.position shouldBe selectPosition
    }

    @Test
    fun `보드에 없는 position로 요청시 null 반환`() {
        val board = fakeBoard(3, 3)
        board.getBlockByPosition(Position.of(9, 9)) shouldBe null
    }
}
