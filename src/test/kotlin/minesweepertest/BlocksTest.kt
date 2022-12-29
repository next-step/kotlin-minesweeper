package minesweepertest

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.ints.shouldBeLessThan
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

                nearbyMineCount shouldBe block.getNearbyMineCount()
            }
        }
    }

    expect("BlockTable에 블록들을 넣고 setUp하면 각 블록들이 주변에 있는 지뢰 개수만큼으로 세팅된다.") {
        val blocks = mapOf(
            (0 to 0) to CleanBlock(0),
            (0 to 1) to CleanBlock(0),
            (0 to 2) to CleanBlock(0),
            (1 to 0) to CleanBlock(0),
            (1 to 1) to CleanBlock(0),
            (1 to 2) to MineBlock(),
            (2 to 0) to CleanBlock(0),
            (2 to 1) to CleanBlock(0),
            (2 to 2) to CleanBlock(0)
        )

        val blockTable = BlockTable(blocks)
        blockTable.setUp()

        val actualBlockTable = BlockTable(
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

        blockTable.record.values.zip(actualBlockTable.record.values) { test, actual ->
            test.getNearbyMineCount() shouldBe actual.getNearbyMineCount()
        }
    }

    expect("BlockTable에 블록들을 넣고 setUp하지 않으면 근처 지뢰 개수가 반영되지 않는다.") {
        val blocks = mapOf(
            (0 to 0) to CleanBlock(0),
            (0 to 1) to CleanBlock(0),
            (0 to 2) to CleanBlock(0),
            (1 to 0) to CleanBlock(0),
            (1 to 1) to CleanBlock(0),
            (1 to 2) to MineBlock(),
            (2 to 0) to CleanBlock(0),
            (2 to 1) to CleanBlock(0),
            (2 to 2) to CleanBlock(0)
        )

        val unSetUpBlockTable = BlockTable(blocks)

        val actualBlockTable = BlockTable(
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

        val matchesWithActual = unSetUpBlockTable.record.values.zip(actualBlockTable.record.values) { test, actual ->
            test.getNearbyMineCount() == actual.getNearbyMineCount()
        }
        matchesWithActual.count { false } shouldBeLessThan actualBlockTable.record.size
    }
})
