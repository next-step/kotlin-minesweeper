package minesweeper.domain.coordinate

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import minesweeper.domain.RandomMineGenerator

class MineCoordinateSystemDecoratorTest : FreeSpec({
    "지뢰 좌표 시스템은" - {

        val height = 10
        val width = 10
        val mineCount = 10

        val boardCoordinateSystem = BoardCoordinateSystem(height = height, width = width)
        val mineGenerator = RandomMineGenerator

        val mineCoordinateSystemDecorator =
            MineCoordinateSystemDecorator(coordinateSystem = boardCoordinateSystem, mineGenerator = mineGenerator, mineCount = mineCount)

        "높이를 확인할 수 있다" {
            mineCoordinateSystemDecorator.height shouldBe 10
        }

        "너비를 확인할 수 있다" {
            mineCoordinateSystemDecorator.width shouldBe 10
        }

        "높이*너비 개수의 좌표 위치값을 확인할 수 있다" {
            mineCoordinateSystemDecorator.coordinate.shouldNotBeNull()
            mineCoordinateSystemDecorator.coordinate.size shouldBe 100
        }

        "지뢰 목록을 확인할 수 있다" {
            mineCoordinateSystemDecorator.mineList.size shouldBe 10
        }

        "생성시 지뢰의 개수가 좌표의 개수보다 크면 에러가 발생한다." {
            shouldThrow<IllegalArgumentException> { MineCoordinateSystemDecorator(coordinateSystem = boardCoordinateSystem, mineGenerator = mineGenerator, mineCount = 101) }
        }
    }
})