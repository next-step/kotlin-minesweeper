package minesweeper.domain

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

/**
 * @see Blocks
 */
class BlocksTest : ExpectSpec({

    expect("전체 블록 x 개와 지뢰로 만드려는 위치를 입력하면 해당 위치를 지뢰로 만든다.") {
        val blocks = Blocks.of(9, listOf(1, 3))

        assertSoftly(blocks.blocks) {
            size shouldBe 9
            count { it is CleanBlock } shouldBe 7
            count { it is MineBlock } shouldBe 2
            get(0).shouldBeInstanceOf<CleanBlock>()
            get(1).shouldBeInstanceOf<MineBlock>()
            get(2).shouldBeInstanceOf<CleanBlock>()
            get(3).shouldBeInstanceOf<MineBlock>()
            get(4).shouldBeInstanceOf<CleanBlock>()
            get(5).shouldBeInstanceOf<CleanBlock>()
            get(6).shouldBeInstanceOf<CleanBlock>()
            get(7).shouldBeInstanceOf<CleanBlock>()
            get(8).shouldBeInstanceOf<CleanBlock>()
        }
    }
})
