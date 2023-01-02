package minesweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Block
import minesweeper.domain.Blocks

class BlocksTest : StringSpec({
    "블록 주변 지뢰개수를 가져온다." {
        val height = 3
        val width = 5
        val mineCount = 8

        val checkBlock1 = Block.Normal()
        val checkBlock2 = Block.Normal()
        val checkBlock3 = Block.Normal()

        val row1 = listOf(Block.LandMine, Block.LandMine, Block.LandMine, Block.Normal(), Block.Normal())
        val row2 = listOf(Block.LandMine, checkBlock1, Block.LandMine, checkBlock2, Block.Normal())
        val row3 = listOf(Block.LandMine, Block.LandMine, Block.LandMine, Block.Normal(), checkBlock3)

        val blocks = Blocks(width, height, listOf(row1, row2, row3).mapIndexed { index, blockList -> index to blockList }.toMap())

        blocks.getAroundMineCount(checkBlock1) shouldBe mineCount
        blocks.getAroundMineCount(checkBlock2) shouldBe 3
        blocks.getAroundMineCount(checkBlock3) shouldBe 0
    }
})
