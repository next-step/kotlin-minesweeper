package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import minesweeper.domain.mine.Mine

class MineTest : StringSpec({
    "mine count 가 1보다 작을 경우 에러를 발생시킨다." {
        shouldThrow<IllegalArgumentException> {
            Mine(mineCount = 0)
        }
    }

    "mine count가 1 이상인 경우 에러를 발생시키지 않는다" {
        Mine(mineCount = 1)
    }
})
