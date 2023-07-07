package minesweeper.game

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.game.Coordinate
import minesweeper.domain.game.position
import minesweeper.domain.game.toMines

class MineAroundTest : StringSpec({

    "주변의 지뢰 갯수 [0,0] [1,1] 지뢰일 경우 [0,1]의 주변 지뢰 갯수는 2개 이다" {
        val mine = mutableListOf(
            Coordinate(0, 0), Coordinate(1, 1), Coordinate(2, 2), Coordinate(3, 3), Coordinate(4, 4)
        ).toMines()
        val count = mine.nearMineCount(0 position 1)
        count shouldBe 2
    }

    "주변의 지뢰 갯수 [0,0] [1,1] 지뢰일 경우 [0,3]의 주변 지뢰 갯수는 0개 이다" {
        val mine = mutableListOf(
            Coordinate(0, 0), Coordinate(1, 1), Coordinate(2, 2), Coordinate(3, 3), Coordinate(4, 4)
        ).toMines()
        val count = mine.nearMineCount(0 position 3)
        count shouldBe 0
    }
})
