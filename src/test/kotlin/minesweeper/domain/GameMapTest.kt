package minesweeper.domain

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

/**
 * @see GameMap
 */
class GameMapTest : ExpectSpec({

    expect("높이, 너비, 지뢰 개수와 맵 세팅 전략을 입력하면 해당하는 GameMap을 생성한다") {
        val height = 3
        val width = 3
        val mineCount = 2
        val fixedMineSettingStrategy = object : MineSettingStrategy {
            override fun getLocations(wholeBlockCount: Int, mineCount: Int): List<Int> {
                return listOf(1, 3)
            }
        }

        val gameMap = GameMap.of(height, width, mineCount, fixedMineSettingStrategy)

        assertSoftly(gameMap.blockTable.record.values) { blocks ->
            blocks.count { it is CleanBlock } shouldBe 7
            blocks.count { it is MineBlock } shouldBe 2

            assertSoftly(blocks.toList()) {
                get(0).shouldBeInstanceOf<CleanBlock>()
                get(1).shouldBeInstanceOf<MineBlock>()
                get(2).shouldBeInstanceOf<CleanBlock>()
                get(3).shouldBeInstanceOf<MineBlock>()
                get(4).shouldBeInstanceOf<CleanBlock>()
                get(5).shouldBeInstanceOf<CleanBlock>()
                get(6).shouldBeInstanceOf<CleanBlock>()
                get(7).shouldBeInstanceOf<CleanBlock>()
                get(8).shouldBeInstanceOf<CleanBlock>()
            }
        }
    }

    context("좌표를 입력했을 때") {
        val height = 3
        val width = 3
        val mineCount = 2
        val fixedMineSettingStrategy = object : MineSettingStrategy {
            override fun getLocations(wholeBlockCount: Int, mineCount: Int): List<Int> {
                return listOf(1, 3)
            }
        }

        val gameMap = GameMap.of(height, width, mineCount, fixedMineSettingStrategy)

        expect("지뢰라면 게임이 끝난다") {
            val mineCord = MapCord(0, 1)

            gameMap.open(mineCord)

            gameMap.getGameProgress() shouldBe GameState.LOSE
            gameMap.isFinished() shouldBe true
        }

        expect("지뢰가 아니고 아직 열리지 않은 일반 블록이 있다면 게임 진행을 계속할 수 있다.") {
            val mineCord = MapCord(0, 0)

            gameMap.open(mineCord)

            gameMap.getGameProgress() shouldBe GameState.PLAYING
            gameMap.isFinished() shouldBe false
        }

        expect("지뢰가 아니고 모든 일반 블록이 열렸다면 게임에 승리한다.") {
            val mineCords = listOf(
                MapCord(0, 0),
                MapCord(0, 2),
                MapCord(2, 0),
                MapCord(2, 2),
            )

            mineCords.forEach { gameMap.open(it) }

            gameMap.getGameProgress() shouldBe GameState.WIN
            gameMap.isFinished() shouldBe true
        }
    }
})
