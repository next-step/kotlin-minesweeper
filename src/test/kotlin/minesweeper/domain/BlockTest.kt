package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.flag.BlockFlag
import minesweeper.domain.flag.MineFlag

class BlockTest : StringSpec({

    "블록 플래그를 가진 블록을 생성하면 주변 지뢰 카운터를 수정할 수 있다." {
        val block = Block(coordinate = Coordinate(x = 1, y = 1), flag = BlockFlag())
        block.flag.currentState() shouldBe "0"

        block.updateBlock(aroundMineCount = 5)
        block.flag.currentState() shouldBe "5"
    }

    "지뢰 플래그를 가진 블록을 생성하면 주변 지뢰 카운터를 수정할 수 없다." {
        val block = Block(coordinate = Coordinate(x = 1, y = 1), flag = MineFlag())
        block.flag.currentState() shouldBe "*"

        block.updateBlock(aroundMineCount = 5)
        block.flag.currentState() shouldBe "*"
    }
})
