package minesweeper.domain

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe

/**
 * @see BlockTable
 */
class BlockTableTest : ExpectSpec({

    expect("BlockTable을 생성하면 각 블록들이 주변에 있는 지뢰 개수만큼으로 세팅된다.") {
        val records = mutableMapOf(
            MapCord(0, 0) to CleanBlock(),
            MapCord(0, 1) to CleanBlock(),
            MapCord(0, 2) to CleanBlock(),
            MapCord(1, 0) to CleanBlock(),
            MapCord(1, 1) to CleanBlock(),
            MapCord(1, 2) to MineBlock(),
            MapCord(2, 0) to CleanBlock(),
            MapCord(2, 1) to CleanBlock(),
            MapCord(2, 2) to CleanBlock()
        )

        val blockTable = BlockTable(records)

        assertSoftly(blockTable.record.values.toList()) {
            get(0).getNearbyMineCount() shouldBe 0
            get(1).getNearbyMineCount() shouldBe 1
            get(2).getNearbyMineCount() shouldBe 1
            get(3).getNearbyMineCount() shouldBe 0
            get(4).getNearbyMineCount() shouldBe 1
            get(6).getNearbyMineCount() shouldBe 0
            get(7).getNearbyMineCount() shouldBe 1
            get(8).getNearbyMineCount() shouldBe 1
        }
    }

    context("근처에 블록이") {
        val mapCords = MapCords(
            listOf(
                MapCord(0, 0), MapCord(0, 1), MapCord(0, 2),
                MapCord(1, 0), MapCord(1, 1), MapCord(1, 2),
                MapCord(2, 0), MapCord(2, 1), MapCord(2, 2)
            )
        )

        expect("한 개 있으면 1이 표시된다.") {
            val blocks = Blocks(
                listOf(
                    CleanBlock(), CleanBlock(), CleanBlock(),
                    CleanBlock(), CleanBlock(), CleanBlock(),
                    CleanBlock(), CleanBlock(), MineBlock(),
                )
            )
            val blockTable = BlockTable.of(mapCords, blocks)

            blockTable.record.values.toList().get(4).getNearbyMineCount() shouldBe 1
        }

        expect("두 개 있으면 2가 표시된다.") {
            val blocks = Blocks(
                listOf(
                    CleanBlock(), CleanBlock(), CleanBlock(),
                    CleanBlock(), CleanBlock(), CleanBlock(),
                    CleanBlock(), MineBlock(), MineBlock(),
                )
            )
            val blockTable = BlockTable.of(mapCords, blocks)

            blockTable.record.values.toList().get(4).getNearbyMineCount() shouldBe 2
        }

        expect("세 개 있으면 3이 표시된다.") {
            val blocks = Blocks(
                listOf(
                    CleanBlock(), CleanBlock(), CleanBlock(),
                    CleanBlock(), CleanBlock(), CleanBlock(),
                    MineBlock(), MineBlock(), MineBlock(),
                )
            )
            val blockTable = BlockTable.of(mapCords, blocks)

            blockTable.record.values.toList().get(4).getNearbyMineCount() shouldBe 3
        }

        expect("네 개 있으면 4가 표시된다.") {
            val blocks = Blocks(
                listOf(
                    CleanBlock(), CleanBlock(), CleanBlock(),
                    CleanBlock(), CleanBlock(), MineBlock(),
                    MineBlock(), MineBlock(), MineBlock(),
                )
            )
            val blockTable = BlockTable.of(mapCords, blocks)

            blockTable.record.values.toList().get(4).getNearbyMineCount() shouldBe 4
        }

        expect("다섯 개 있으면 5가 표시된다.") {
            val blocks = Blocks(
                listOf(
                    CleanBlock(), CleanBlock(), CleanBlock(),
                    MineBlock(), CleanBlock(), MineBlock(),
                    MineBlock(), MineBlock(), MineBlock(),
                )
            )
            val blockTable = BlockTable.of(mapCords, blocks)

            blockTable.record.values.toList().get(4).getNearbyMineCount() shouldBe 5
        }

        expect("여섯 개 있으면 6이 표시된다.") {
            val blocks = Blocks(
                listOf(
                    CleanBlock(), CleanBlock(), MineBlock(),
                    MineBlock(), CleanBlock(), MineBlock(),
                    MineBlock(), MineBlock(), MineBlock(),
                )
            )
            val blockTable = BlockTable.of(mapCords, blocks)

            blockTable.record.values.toList().get(4).getNearbyMineCount() shouldBe 6
        }

        expect("일곱 개 있으면 7이 표시된다.") {
            val blocks = Blocks(
                listOf(
                    CleanBlock(), MineBlock(), MineBlock(),
                    MineBlock(), CleanBlock(), MineBlock(),
                    MineBlock(), MineBlock(), MineBlock(),
                )
            )
            val blockTable = BlockTable.of(mapCords, blocks)

            blockTable.record.values.toList().get(4).getNearbyMineCount() shouldBe 7
        }

        expect("여덟 개 있으면 8이 표시된다.") {
            val blocks = Blocks(
                listOf(
                    MineBlock(), MineBlock(), MineBlock(),
                    MineBlock(), CleanBlock(), MineBlock(),
                    MineBlock(), MineBlock(), MineBlock(),
                )
            )
            val blockTable = BlockTable.of(mapCords, blocks)

            blockTable.record.values.toList().get(4).getNearbyMineCount() shouldBe 8
        }
    }
})
