package minesweeper

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Blocks
import minesweeper.domain.CleanBlock
import minesweeper.domain.MineBlock

/**
 * @see
 */
class BlocksTest : ExpectSpec({

    expect("전체 블록 x 개와 지뢰 y 개를 입력하면 일반 블록 x개 만들고 마지막 y개를 지뢰로 만든다.") {
        val blocks = Blocks.of(9, 2)

        assertSoftly(blocks.blocks) {
            size shouldBe 9
            count { it is CleanBlock } shouldBe 7
            count { it is MineBlock } shouldBe 2
        }
    }
})
