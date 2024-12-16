package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MineSweeperMapBlocksTest {
    @Test
    fun `보드의 블록이 지뢰인지 여부를 알수있다`() {
        val board = MutableList(8) { MineSweeperMapBlock() }
        board.add(MineSweeperMapBlock(_isMine = true))
        val mineSweeperMapBlocks = MineSweeperMapBlocks(board)

        mineSweeperMapBlocks.isMine(8) shouldBe true
    }

    @Test
    fun `보드의 블록을 지뢰로 설정 할수있다`() {
        val board = MutableList(8) { MineSweeperMapBlock() }
        val mineSweeperMapBlocks = MineSweeperMapBlocks(board)

        mineSweeperMapBlocks.setMine(0)

        mineSweeperMapBlocks.isMine(0) shouldBe true
    }

    @Test
    fun `보드사이즈를 넘어가는 숫자는 해당 클래스에서 사용할수없다`() {
        val board = MutableList(8) { MineSweeperMapBlock() }
        val mineSweeperMapBlocks = MineSweeperMapBlocks(board)

        shouldThrow<IllegalArgumentException> { mineSweeperMapBlocks.isMine(8) }.apply { message shouldBe "보드 밖에 있어요" }
        shouldThrow<IllegalArgumentException> { mineSweeperMapBlocks.setMine(8) }.apply { message shouldBe "보드 밖에 있어요" }
        shouldThrow<IllegalArgumentException> { mineSweeperMapBlocks.getMineAroundCount(8) }.apply { message shouldBe "보드 밖에 있어요" }
        shouldThrow<IllegalArgumentException> { mineSweeperMapBlocks.increaseMineAroundCount(8) }.apply { message shouldBe "보드 밖에 있어요" }
        shouldThrow<IllegalArgumentException> { mineSweeperMapBlocks.isOpen(8) }.apply { message shouldBe "보드 밖에 있어요" }
        shouldThrow<IllegalArgumentException> { mineSweeperMapBlocks.open(8) }.apply { message shouldBe "보드 밖에 있어요" }
    }
}
