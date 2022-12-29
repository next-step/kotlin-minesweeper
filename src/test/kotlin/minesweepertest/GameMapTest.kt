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
                mutableListOf(CleanBlock(0), CleanBlock(1), CleanBlock(1)),
                mutableListOf(CleanBlock(0), CleanBlock(1), MineBlock()),
                mutableListOf(CleanBlock(0), CleanBlock(1), CleanBlock(1)),
            )
        )

        val height = gameMap.blocks.size
        val width = gameMap.blocks[0].size

        for (x in 0 until width) {
            for (y in 0 until height) {
                if (gameMap.blocks[y][x] is MineBlock) continue

                val mineCountNearby = listOf(
                    gameMap.blocks.getOrNull(y - 1)?.getOrNull(x - 1),
                    gameMap.blocks.getOrNull(y - 1)?.getOrNull(x),
                    gameMap.blocks.getOrNull(y - 1)?.getOrNull(x + 1),
                    gameMap.blocks.getOrNull(y)?.getOrNull(x - 1),
                    gameMap.blocks[y][x],
                    gameMap.blocks.getOrNull(y)?.getOrNull(x + 1),
                    gameMap.blocks.getOrNull(y + 1)?.getOrNull(x - 1),
                    gameMap.blocks.getOrNull(y + 1)?.getOrNull(x),
                    gameMap.blocks.getOrNull(y + 1)?.getOrNull(x + 1),
                ).count { it is MineBlock }

                val actualBlock = gameMap.blocks[y][x]
                if(actualBlock is CleanBlock) {
                    actualBlock.getValue() shouldBe mineCountNearby
                }
            }
        }
    }
})
