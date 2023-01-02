package minesweeper.domain.coordinate

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class BaseCoordinateSystemTest : FreeSpec({
    "보드 좌표 시스템은" - {
        val height = 10
        val width = 10

        val baseCoordinateSystem = BaseCoordinateSystem(height = height, width = width)

        "높이를 확인할 수 있다" {
            baseCoordinateSystem.height shouldBe 10
        }

        "너비를 확인할 수 있다" {
            baseCoordinateSystem.width shouldBe 10
        }

        "높이*너비 개수의 좌표 위치값을 확인할 수 있다" {
            baseCoordinateSystem.coordinate.shouldNotBeNull()
            baseCoordinateSystem.coordinate.size shouldBe 100
        }
    }
})
