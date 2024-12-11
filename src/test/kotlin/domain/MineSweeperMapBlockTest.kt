package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MineSweeperMapBlockTest {
    @Test
    fun `지뢰찾기 칸은 지뢰인지 여부를 가진다`() {
        val mineSweeperBlock = MineSweeperMapBlock(isMine = true)
        mineSweeperBlock.isMine shouldBe true
    }
}
