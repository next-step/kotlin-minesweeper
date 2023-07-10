package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GameMapTest : StringSpec({
    "입력받은 위치는 높이와 넓이보다 작거나 같아야한다" {
        val height = 10
        val width = 10
        val gameMap = GameMap(height.toNumber() to width.toNumber(), 10.toNumber())
        gameMap.open(MinePosition.of(1,1)) shouldBe true
        gameMap.open(MinePosition.of(height, width)) shouldBe true
        shouldThrow<IllegalArgumentException> {
            gameMap.open(MinePosition.of(11, 11))
        }
    }
    "각 사각형에 표시될 숫자는 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수다." {
        val gameMap = GameMap(3.toNumber() to 3.toNumber(), 1.toNumber())
        val minePosition = MinePosition.of(2,2)
        gameMap.generateMine(object: MinePositionGenerator {
            override fun generatePosition(): MinePosition {
                return minePosition
            }
        })

        val positions = listOf (
            MinePosition.of(1, 1),
            MinePosition.of(1, 3),
            MinePosition.of(3, 1),
            MinePosition.of(3, 3)
        )
        positions.forEach {
            println(it)
            gameMap.mineColumns.get(it).nearMineCount shouldBe 1
        }
    }

    "지뢰를 열면 게임 종료" {
        val gameMap = GameMap(1.toNumber() to 2.toNumber(), 1.toNumber())
        val minePosition = MinePosition.of(1,1)
        gameMap.generateMine(object: MinePositionGenerator {
            override fun generatePosition(): MinePosition {
                return minePosition
            }
        })
        gameMap.open(MinePosition.of(1,1)) shouldBe false
    }
})
