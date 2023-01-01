package minesweeper.domain

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe

/**
 * @see CleanBlock
 */
class BlockTest : ExpectSpec({

    expect("자기 주변에 지뢰 블록 개수를 알 수 있다") {

        val mainCleanBlock = CleanBlock()
        val cleanBlock1 = CleanBlock()
        val cleanBlock2 = CleanBlock()
        val cleanBlock3 = CleanBlock()
        val mineBlock1 = MineBlock()
        val mineBlock2 = MineBlock()

        mainCleanBlock.addNearBlocks(
            listOf(cleanBlock1, cleanBlock2, cleanBlock3, mineBlock1, mineBlock2)
        )

        mainCleanBlock.getNearbyMineCount() shouldBe 2
    }
})
