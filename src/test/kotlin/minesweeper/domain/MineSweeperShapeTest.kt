package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.mine.MineSweeperShape

class MineSweeperShapeTest : StringSpec({
    "isMine 함수 지뢰 찾기 테스트" {
        val mine = "*"
        MineSweeperShape.isMine(mine) shouldBe true
    }

    "isMine 함수 지뢰가 아닌 경우 테스트" {
        val mine = "0"
        MineSweeperShape.isMine(mine) shouldBe false
    }
})
