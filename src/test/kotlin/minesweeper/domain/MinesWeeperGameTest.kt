package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MinesWeeperGameTest : StringSpec({
    "입력받은 위치는 높이와 넓이보다 작거나 같아야한다" {
        val height = 10
        val width = 10
        val gameMap = MinesWeeperGame(height.toNumber() to width.toNumber(), 10.toNumber())
        gameMap.openTile(object : GameStateNotify {
            override fun getOpenPosition(): MinePosition {
                return MinePosition.of(1, 1)
            }

            override fun showGameState(status: GameStatus) {
                status shouldBe GameStatus.CONTINUE
            }

            override fun showMineMapInProgress(mineMap: MinesMap) {
            }
        })
        gameMap.openTile(object : GameStateNotify {
            override fun getOpenPosition(): MinePosition {
                return MinePosition.of(11, 11)
            }

            override fun showGameState(status: GameStatus) {
            }

            override fun showMineMapInProgress(mineMap: MinesMap) {
            }
        })
    }
    "각 사각형에 표시될 숫자는 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수다." {
        val gameMap = MinesWeeperGame(3.toNumber() to 3.toNumber(), 1.toNumber())
        val minePosition = MinePosition.of(1, 1)
        gameMap.generateMine(object : MinePositionGenerator {
            override fun generatePosition(): MinePosition {
                return minePosition
            }
        })

        val positions = listOf(
            MinePosition.of(0, 0),
            MinePosition.of(0, 2),
            MinePosition.of(2, 0),
            MinePosition.of(2, 2)
        )
        positions.forEach {
            println(it)
            gameMap.minesMap.get(it).nearMineCount shouldBe 1
        }
    }

    "지뢰를 열면 게임 종료" {
        val gameMap = MinesWeeperGame(1.toNumber() to 2.toNumber(), 1.toNumber())
        val minePosition = MinePosition.of(0, 0)
        gameMap.generateMine(object : MinePositionGenerator {
            override fun generatePosition(): MinePosition {
                return minePosition
            }
        })
        gameMap.openTile(object : GameStateNotify {
            override fun getOpenPosition(): MinePosition {
                return MinePosition.of(0, 0)
            }

            override fun showGameState(status: GameStatus) {
                status shouldBe GameStatus.LOSE
            }

            override fun showMineMapInProgress(mineMap: MinesMap) {
            }
        })
    }
    "지뢰 이외의 모든 곳을 열면 게임 종료" {
        val gameMap = MinesWeeperGame(1.toNumber() to 2.toNumber(), 1.toNumber())
        val minePosition = MinePosition.of(0, 0)
        gameMap.generateMine(object : MinePositionGenerator {
            override fun generatePosition(): MinePosition {
                return minePosition
            }
        })
        gameMap.openTile(object : GameStateNotify {
            override fun getOpenPosition(): MinePosition {
                return MinePosition.of(0, 1)
            }

            override fun showGameState(status: GameStatus) {
                status shouldBe GameStatus.WIN
            }

            override fun showMineMapInProgress(mineMap: MinesMap) {
            }
        })
    }
})
