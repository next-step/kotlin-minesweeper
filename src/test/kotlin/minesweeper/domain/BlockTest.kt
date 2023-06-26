package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.flag.BlockState
import minesweeper.domain.flag.MineFlag

class BlockTest : StringSpec({

    "블록을 열 때 히든 상태이면 열리지만, 이미 열린 상태라면 이미 열린 상태 값을 반환한다." {
        val block = Block(coordinate = Coordinate(x = 1, y = 1), flag = MineFlag())

        block.open() shouldBe BlockState.MINE
        block.open() shouldBe BlockState.ALREADY_OPEN
    }
})
