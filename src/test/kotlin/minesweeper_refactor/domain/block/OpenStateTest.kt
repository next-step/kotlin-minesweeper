package minesweeper_refactor.domain.block

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class OpenStateTest : StringSpec({

    "블록 상태로 오픈 상태를 반환할 수 있다." {
        forAll(
            row(BlockState.SEVEN, OpenState.DO_NOTHING),
            row(BlockState.ONE, OpenState.DO_NOTHING),
            row(BlockState.ZERO, OpenState.CHAIN_OPEN),
            row(BlockState.MINE, OpenState.MINE),
        ) { value, expect ->
            val openState = OpenState.valueOf(blockState = value)

            openState shouldBe expect
        }
    }
})
