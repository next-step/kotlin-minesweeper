package minesweepertest

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe

/**
 * @see GameMap
 */
class GameMapTest : ExpectSpec({

    expect("GameMap의 각 블록은 주변에 지뢰 개수를 나타낸다.") {

        val gameMap = GameMap(
            listOf(
                mutableListOf(0, 1, 1),
                mutableListOf(0, 1, "*"),
                mutableListOf(0, 1, 1),
            )
        )

        val height = gameMap.blocks.size
        val width = gameMap.blocks[0].size

        for (x in 0 until width) {
            for (y in 0 until height) {
                if (gameMap.blocks[y][x] == "*") continue

                val mineCountNearby = listOf(
                    gameMap.blocks.getOrNull(y - 1)?.getOrNull(x - 1) ?: 0,
                    gameMap.blocks.getOrNull(y - 1)?.getOrNull(x) ?: 0,
                    gameMap.blocks.getOrNull(y - 1)?.getOrNull(x + 1) ?: 0,
                    gameMap.blocks.getOrNull(y)?.getOrNull(x - 1) ?: 0,
                    gameMap.blocks[y][x],
                    gameMap.blocks.getOrNull(y)?.getOrNull(x + 1) ?: 0,
                    gameMap.blocks.getOrNull(y + 1)?.getOrNull(x - 1) ?: 0,
                    gameMap.blocks.getOrNull(y + 1)?.getOrNull(x) ?: 0,
                    gameMap.blocks.getOrNull(y + 1)?.getOrNull(x + 1) ?: 0,
                ).count { it == "*" }

                gameMap.blocks[y][x] shouldBe mineCountNearby
            }
        }
    }
})
