package domain

import domain.vo.Height
import domain.vo.MineCount
import domain.vo.Width
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

internal class GameTest : FreeSpec({

    "지뢰 개수가 보드 크기보다 크면 에러가 발생한다" {
        val width = Width(10)
        val height = Height(10)
        val mineCount = MineCount(101)

        shouldThrow<IllegalArgumentException> {
            Game.of(width, height, mineCount)
        }
    }

    "주어진 값을 활용해 보드를 생성한다" {
        val width = Width(10)
        val height = Height(5)
        val mineCount = MineCount(20)

        val game = Game.of(width, height, mineCount)

        game.board.mineCount shouldBe mineCount.value
        game.board.cellCount shouldBe width.value * height.value
    }
})
