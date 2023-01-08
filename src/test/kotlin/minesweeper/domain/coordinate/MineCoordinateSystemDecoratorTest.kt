package minesweeper.domain.coordinate

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import minesweeper.domain.RandomMineGenerator

class MineCoordinateSystemDecoratorTest : FreeSpec({
    "지뢰 좌표 시스템은" - {

        val height = 10
        val width = 10
        val mineCount = 10

        val baseCoordinateSystem = BaseCoordinateSystem(height = height, width = width)
        val mineGenerator = RandomMineGenerator

        val mineCoordinateSystemDecorator = MineCoordinateSystemDecorator(baseCoordinateSystem = baseCoordinateSystem, mineGenerator = mineGenerator, mineCount = mineCount)

        "지뢰 목록을 확인할 수 있다" {
            mineCoordinateSystemDecorator.mines.shouldNotBeNull()
        }

        "생성시 지뢰의 개수가 좌표의 개수보다 크면 에러가 발생한다." {
            shouldThrow<IllegalArgumentException> { MineCoordinateSystemDecorator(baseCoordinateSystem = baseCoordinateSystem, mineGenerator = mineGenerator, mineCount = 101) }
        }
    }
})