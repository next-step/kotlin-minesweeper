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
        mineSweeperMap.setMine(3, 3)
        mineSweeperMap.getMineAroundCount(0) shouldBe 1
        mineSweeperMap.getMineAroundCount(1) shouldBe 1
        mineSweeperMap.getMineAroundCount(2) shouldBe 1
        mineSweeperMap.getMineAroundCount(5) shouldBe 1
        mineSweeperMap.getMineAroundCount(7) shouldBe 1
        mineSweeperMap.getMineAroundCount(10) shouldBe 1
        mineSweeperMap.getMineAroundCount(11) shouldBe 1
        mineSweeperMap.getMineAroundCount(12) shouldBe 2
        mineSweeperMap.getMineAroundCount(17) shouldBe 1
        mineSweeperMap.getMineAroundCount(19) shouldBe 1
        mineSweeperMap.getMineAroundCount(13) shouldBe 1
        mineSweeperMap.getMineAroundCount(14) shouldBe 1
        mineSweeperMap.getMineAroundCount(23) shouldBe 1
        mineSweeperMap.getMineAroundCount(22) shouldBe 1
        mineSweeperMap.getMineAroundCount(24) shouldBe 1
    }

    @Test
    fun `한블록을 오픈하면 인접한 블록이 모두 열린다`() {
        val mineSweeperMap =
            MineSweeperMap(MineSweeperMapShape(2, 2), MineSweeperMapBlocks(MutableList(25) { MineSweeperMapBlock() }))
        mineSweeperMap.setMine(1, 1)
        mineSweeperMap.open(0, 0)

        mineSweeperMap.isOpen(0, 0) shouldBe true
        mineSweeperMap.isOpen(1, 0) shouldBe true
        mineSweeperMap.isOpen(0, 1) shouldBe true
    }
}
