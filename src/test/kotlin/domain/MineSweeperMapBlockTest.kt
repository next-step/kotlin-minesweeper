package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MineSweeperMapBlockTest {
    @Test
    fun `지뢰찾기 칸은 지뢰인지 여부를 가진다`() {
        val mineSweeperBlock = MineSweeperMapBlock(true)
        mineSweeperBlock.isMine shouldBe true
    }

    @Test
    fun `지뢰찾기 칸은 주변의 지뢰 수를 가진다`() {
        val mineSweeperMapBlock = MineSweeperMapBlock(false, 1)
        mineSweeperMapBlock.mineAroundCount shouldBe 1
    }
}
