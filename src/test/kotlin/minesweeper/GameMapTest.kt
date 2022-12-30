package minesweeper

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe

/**
 * @see GameMap
 */
class GameMapTest : ExpectSpec({

    expect("높이, 너비, 지뢰 개수를 입력하면 해당하는 GameMap을 생성한다") {
        val height = 3
        val width = 3
        val mineCount = 2

        val gameMap = GameMap.of(height, width, mineCount)

        assertSoftly(gameMap.blockTable.record.values) { blocks ->
            blocks.count { it is CleanBlock } shouldBe 7
            blocks.count { it is MineBlock } shouldBe 2
        }
    }
})
