package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MineSweeperGameTest {
    @Test
    fun `지뢰찾기 보드, 지뢰의 갯수를 가진다`() {
        val map = MineSweeperMap(2, 2, Array(2) { Array(2) { MineSweeperMapBlock(false) } })
        val game = MineSweeperGame(map, 1)

        game.mineSweeperMap.map.get(0).get(0).isMine shouldBe false
        game.mineCount shouldBe 1
    }

    @Test
    fun `게임은 맵에서의 지뢰의 위치를 결정한다(중복없이)`() {
        val map = MineSweeperMap(2, 2, Array(2) { Array(2) { MineSweeperMapBlock(false) } })
        val game = MineSweeperGame(map, 1) { map: MineSweeperMap -> map.map.get(0).get(0).isMine = true }
        game.setMinePosition()
        game.mineSweeperMap.map.get(0).get(0).isMine shouldBe true
    }
}
