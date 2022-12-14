package minesweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlockRepositoryTest : StringSpec({
    "입력 받은 높이와 넓이에 맞게 블록을 가져온다" {
        val height = 3
        val width = 5
        val repository = BlockRepository(height, width, 3)

        repository.blocks.size shouldBe height
        repository.blocks[0].size shouldBe width
    }

    "입력 받은 지뢰 개수에 맞게 지뢰를 가져온다" {
        val mineCount = 3
        val repository = BlockRepository(3, 5, mineCount)

        val landMineBlocks = repository.blocks.flatten().filterIsInstance<Block.LandMine>()

        landMineBlocks.size shouldBe mineCount
    }
})
