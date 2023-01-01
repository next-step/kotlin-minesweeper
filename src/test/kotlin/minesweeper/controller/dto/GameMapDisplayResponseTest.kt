package minesweeper.controller.dto

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.GameMap
import minesweeper.domain.RandomMineSettingStrategy
import minesweeper.view.model.CleanBlockViewModel
import minesweeper.view.model.MineBlockViewModel

/**
 * @see GameMapDisplayResponse
 */
class GameMapDisplayResponseTest : ExpectSpec({

    expect("GameMap을 입력하면 해당 맵에 있는 일반 블록과 지뢰 블록을 ViewModel로 변경해준다.") {
        val gameMap = GameMap.of(3, 3, 2, RandomMineSettingStrategy())
        val gameMapDisplayResponse = GameMapDisplayResponse.from(gameMap)

        with(gameMapDisplayResponse.blocksModels) {
            size shouldBe 9
            count { it is CleanBlockViewModel } shouldBe 7
            count { it is MineBlockViewModel } shouldBe 2
        }
    }
})
