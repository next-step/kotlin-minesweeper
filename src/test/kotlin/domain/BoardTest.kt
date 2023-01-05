package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun `보드의 position에 해당하는 블록을 가져온다`() {
        val selectPosition = Position.of(1, 2)
        val blocks = fakeBlocks(positions(3, 3), selectPosition)
        val board = Board(blocks)
        board.getBlockByPosition(selectPosition)?.position shouldBe selectPosition
    }

    @Test
    fun `보드에 없는 position로 요청시 null 반환`() {
        val board = fakeBoard(3, 3)
        board.getBlockByPosition(Position.of(9, 9)) shouldBe null
    }

    @Test
    fun `open시 해당 블록 isVisible은 true로 변경`() {
        val position = Position.of(1, 1)
        val board = fakeBoard(3, 3)
        board.getBlockByPosition(position)?.visible shouldBe false
        val block = board.open(position).getBlockByPosition(position)
        block?.visible shouldBe true
    }

    /**
     * 0 0 0
     * 0 1 1
     * 0 1 C
     * 0 1 C
     */
    @Test
    fun `open시, 지뢰가 아니면, 지뢰가 아닌 주변 블록까지 open한다`() {
        val position = Position.of(1, 1)
        var board = fakeBoard(3, 4, 3 to 3)
        board = board.open(position)
        board.getBlocks().map {
            if (listOf(Position.of(3, 3), Position.of(3, 4)).contains(it.key)) {
                it.value.visible shouldBe false
            } else {
                it.value.visible shouldBe true
            }
        }
    }

    /**
     * 0 0 0
     * 0 1 1
     * 0 1 *
     */
    @Test
    fun `open시, 지뢰이면, 블록 전체가 open된다`() {
        val position = Position.of(3, 3)
        val board = fakeBoard(3, 3, 3 to 3)
        board.open(position).getBlocks().map {
            it.value.visible shouldBe true
        }
    }
}
