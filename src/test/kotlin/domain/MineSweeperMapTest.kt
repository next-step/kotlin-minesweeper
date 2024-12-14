package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MineSweeperMapTest {
    @Test
    fun `높이와 너비에 행렬형태로 보드마다 칸의 상태를 가진다`() {
        val map =
            MineSweeperMap(MineSweeperMapShape(2, 2), MineSweeperMapBlocks(MutableList(4) { MineSweeperMapBlock(false) }))
        map.getWidth() shouldBe 2
        map.getHeight() shouldBe 2
        map.isMine(0, 0) shouldBe false
    }

    @Test
    fun `지뢰 주변 블록에 1을 더한다`() {
        val mineSweeperMap =
            MineSweeperMap(
                MineSweeperMapShape(5, 5),
                MineSweeperMapBlocks(MutableList(25) { MineSweeperMapBlock(false) }),
            )
        mineSweeperMap.setMine(1, 1)
        mineSweeperMap.getMineAroundCount(0) shouldBe 1
        mineSweeperMap.getMineAroundCount(1) shouldBe 1
        mineSweeperMap.getMineAroundCount(2) shouldBe 1
        mineSweeperMap.getMineAroundCount(5) shouldBe 1
        mineSweeperMap.getMineAroundCount(7) shouldBe 1
        mineSweeperMap.getMineAroundCount(10) shouldBe 1
        mineSweeperMap.getMineAroundCount(11) shouldBe 1
        mineSweeperMap.getMineAroundCount(12) shouldBe 1
    }
}
