package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MineSweeperGameTest {
    @Test
    fun `지뢰찾기 보드, 지뢰의 갯수를 가진다`() {
        val map =
            MineSweeperMap(MineSweeperMapShape(3, 2), MineSweeperMapBlocks(MutableList(6) { MineSweeperMapBlock(false) }))
        val game = MineSweeperGame(map, 1)

        game.mineSweeperMap.isMine(0, 0) shouldBe false
        game.mineCount shouldBe 1
    }

    @Test
    fun `게임은 맵에서의 지뢰의 위치를 결정한다(중복없이)`() {
        val map =
            MineSweeperMap(MineSweeperMapShape(3, 2), MineSweeperMapBlocks(MutableList(6) { MineSweeperMapBlock(false) }))
        val game = MineSweeperGame(map, 1)
        game.setMinePosition { map.setMine(2, 1) }
        game.mineSweeperMap.isMine(2, 1) shouldBe true
    }

    @Test
    fun ofTest() {
        val game = MineSweeperGame.of(5, 4, 1) { map -> map.setMine(0, 0) }
        game.mineSweeperMap.isMine(0, 0) shouldBe true
    }
}
