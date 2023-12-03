package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.mine.MineSweeperShape

class MineSweeperWidthTest : StringSpec({
    "생성한 MineSweeperWidth의 Shape는 기본적으로 MineSweeperShape.SHAPE 이다." {
        val notMineSweeperWidth = MineSweeperWidth.newInstance(4)
        notMineSweeperWidth.forEach { shape ->
            shape shouldBe MineSweeperShape.SHAPE.shape
        }
    }

    "MineShape.MINE 을 주면, 마인을 가진 Width를 생성한다" {
        val mineSweeperWidth = MineSweeperWidth.newInstance(widthSize = 4, mineSweeperShape = MineSweeperShape.MINE)
        mineSweeperWidth.forEach { shape ->
            shape shouldBe MineSweeperShape.MINE.shape
        }
    }

    "입력한 width만큼 mineSweeperWidth 의 width가 생성된다" {
        val input = 100
        val mineSweeperWidth = MineSweeperWidth.newInstance(input)
        mineSweeperWidth.size shouldBe input
    }
})
