package domain

import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThatThrownBy
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

    @Test
    fun `지뢰는 음수개일수 없다`() {
        assertThatThrownBy { MineSweeperGame.of(5, 5, -1) }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("지뢰는 음수개 일수 없음")
    }

    @Test
    fun `모든 지뢰를 제외한 칸을 열면 승리`() {
        val mineSweeperMap =
            MineSweeperMap(MineSweeperMapShape(2, 2), MineSweeperMapBlocks(MutableList(4) { MineSweeperMapBlock() }))
        val mineSweeperGame = MineSweeperGame(mineSweeperMap, 0)
        mineSweeperGame.open(0, 0)

        mineSweeperGame.isWin() shouldBe true
    }

    @Test
    fun `지뢰칸을 열면 패배`() {
        val map = MutableList(3) { MineSweeperMapBlock() }
        map.add(MineSweeperMapBlock(_isMine = true))
        val mineSweeperMap =
            MineSweeperMap(MineSweeperMapShape(2, 2), MineSweeperMapBlocks(map))
        val mineSweeperGame = MineSweeperGame(mineSweeperMap, 0)
        mineSweeperGame.open(1, 1)
        mineSweeperGame.isLose() shouldBe true
    }
}
