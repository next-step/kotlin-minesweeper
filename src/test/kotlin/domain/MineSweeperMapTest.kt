package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MineSweeperMapTest {
    @Test
    fun `높이와 너비에 행렬형태로 보드마다 칸의 상태를 가진다`() {
        val mineSweeperMap = MineSweeperMap(2, 2, Array(2) { Array(2) { MineSweeperMapBlock(false) } })
        mineSweeperMap.width shouldBe 2
        mineSweeperMap.height shouldBe 2
        mineSweeperMap.map.get(0).get(0).isMine shouldBe false
    }
}
