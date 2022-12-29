package minesweepertest

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe

/**
 * @see BlockTable
 */
class BlocksTest : ExpectSpec({

    expect("BlockTable의 각 블록은 주변에 지뢰 개수를 나타낸다.") {

        val blockTable = BlockTable(
            mapOf(
                (0 to 0) to CleanBlock(0),
                (0 to 1) to CleanBlock(1),
                (0 to 2) to CleanBlock(1),
                (1 to 0) to CleanBlock(0),
                (1 to 1) to CleanBlock(1),
                (1 to 2) to MineBlock(),
                (2 to 0) to CleanBlock(0),
                (2 to 1) to CleanBlock(1),
                (2 to 2) to CleanBlock(1)
            )
        )

        blockTable.record.entries.forEach { (cord, block) ->
            if (block is CleanBlock) {
                val nearbyMineCount = listOf(
                    (cord.first - 1 to cord.second - 1),
                    (cord.first - 1 to cord.second),
                    (cord.first - 1 to cord.second + 1),
                    (cord.first to cord.second - 1),
                    (cord.first to cord.second),
                    (cord.first to cord.second + 1),
                    (cord.first + 1 to cord.second),
                    (cord.first + 1 to cord.second + 1),
                ).count { blockTable.record[it] is MineBlock }

                nearbyMineCount shouldBe block.getValue()
            }
        }
    }
})
