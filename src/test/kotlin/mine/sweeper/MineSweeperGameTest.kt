package mine.sweeper

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import mine.sweeper.domain.MapSize
import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.MineCount
import mine.sweeper.domain.value.Width

class MineSweeperGameTest : StringSpec({
    "지뢰 찾기 게임에서 맵의 상태를 표출할 수 있다." {
        val mineSweeperGame = MineSweeperGame(MapSize(Height(5), Width(5)), MineCount(3))

        shouldNotThrowAny {
            mineSweeperGame.printEntireMap()
        }
    }

    "맵 높이가 0보다 낮다면 IllegalArgument 에외가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            MineSweeperGame(MapSize(Height(0), Width(5)), MineCount(3))
        }
    }

    "맵 너비가 0보다 낮다면 IllegalArgument 에외가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            MineSweeperGame(MapSize(Height(5), Width(0)), MineCount(3))
        }
    }

    "지뢰가 맵 크기보다 커서 더이상 지뢰를 심을 수 없다면 IllegalStateException 에외가 발생한다." {
        val mapSize = MapSize(Height(5), Width(5))

        shouldThrow<IllegalStateException> {
            MineSweeperGame(mapSize, MineCount(9999))
        }
    }

    "지뢰가 하나도 없다면 IllegalArgument 에외가 발생한다" {
        val mapSize = MapSize(Height(5), Width(5))
        shouldThrow<IllegalArgumentException> {
            MineSweeperGame(mapSize, MineCount(0))
        }
    }
})
