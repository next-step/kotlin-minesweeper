package minesweeper_refactor.domain.block

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper_refactor.domain.block.flag.MineFlag
import minesweeper_refactor.domain.block.flag.NumberFlag
import minesweeper_refactor.domain.coordinate.Coordinate

class BlockTest : StringSpec({

    "블록을 열 때 히든 상태이면 열리지만, 이미 열린 상태라면 아무런 행동을 하지 않는 상태값을 반환한다." {
        val block = Block(coordinate = Coordinate(x = 1, y = 1), flag = MineFlag)

        block.open() shouldBe OpenState.MINE
        block.open() shouldBe OpenState.DO_NOTHING
    }

    "블록을 열 때 값이 0이라면 연쇄 오픈 상태값을 반환한다." {
        val block = Block(coordinate = Coordinate(x = 1, y = 1), flag = NumberFlag(value = 0))

        block.open() shouldBe OpenState.CHAIN_OPEN
    }

    "블록은 현재 열린 상태와 열리지 않은 상태를 반환한다." {
        val block = Block(coordinate = Coordinate(x = 1, y = 1), flag = NumberFlag(value = 0))

        block.isHidden shouldBe true

        block.open()

        block.isHidden shouldBe false
    }

    "블록은 지뢰 여부를 반환한다." {
        val block = Block(coordinate = Coordinate(x = 1, y = 1), flag = NumberFlag(value = 0))

        block.isMine shouldBe false
    }
})
