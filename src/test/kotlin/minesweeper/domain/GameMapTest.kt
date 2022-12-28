package minesweeper.domain

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe

/**
 * @see GameMap
 */
class GameMapTest : ExpectSpec({

    expect("높이, 너비, 지뢰 개수를 입력하면 해당하는 맵을 생성한다") {
        val height = 3
        val width = 3
        val mineCount = 3

        val gameMap = GameMap.of(height, width, mineCount)

        gameMap.width shouldBe 3
        gameMap.height shouldBe 3
    }
})
