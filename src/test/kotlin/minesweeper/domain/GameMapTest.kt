package minesweeper.domain

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.every
import io.mockk.mockk

/**
 * @see GameMap
 */
class GameMapTest : ExpectSpec({

    expect("높이, 너비, 지뢰 개수와 맵 세팅 전략을 입력하면 해당하는 GameMap을 생성한다") {
        val height = 3
        val width = 3
        val mineCount = 2
        val mockRandomMineSettingStrategy = mockk<RandomMineSettingStrategy>()
        every { mockRandomMineSettingStrategy.getLocations(any(), any()) } returns listOf(1, 3)

        val gameMap = GameMap.of(height, width, mineCount, mockRandomMineSettingStrategy)

        assertSoftly(gameMap.blockTable.record.values) { blocks ->
            blocks.count { it is CleanBlock } shouldBe 7
            blocks.count { it is MineBlock } shouldBe 2

            assertSoftly(blocks.toList()) {
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
    }
})
