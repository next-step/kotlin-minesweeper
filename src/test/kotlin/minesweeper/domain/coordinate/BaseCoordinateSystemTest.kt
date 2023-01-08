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

        "최대 x 좌표 및 y 좌표를 확인할 수 있다" - {

            "최대 x 좌표는 좌표의 길이보다 1이 작은 수 이다" {
                baseCoordinateSystem.maxX.value shouldBe width - 1
            }

            "최대 y 좌표는 좌표의 길이보다 1이 작은 수 이다" {
                baseCoordinateSystem.maxY.value shouldBe height - 1
            }
        }
    }
})
