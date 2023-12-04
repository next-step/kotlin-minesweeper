package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.mine.Mine
import minesweeper.domain.mine.MineSweeperShape

class MineTest : StringSpec({
    "mine count 가 1보다 작을 경우 에러를 발생시킨다." {
        shouldThrow<IllegalArgumentException> {
            Mine(mineCount = 0)
        }
    }

    "mine count가 1 이상인 경우 에러를 발생시키지 않는다" {
        Mine(mineCount = 1)
    }

    "mine 기본 생성 시 shape는 MineSweeperShape.MINE 이다" {
        val mine = Mine(mineCount = 1)
        mine.mineShape shouldBe MineSweeperShape.MINE
    }
})
