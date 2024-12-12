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
}
