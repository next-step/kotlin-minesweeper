package dto

import domain.Coordinate
import domain.CoordinatePoint
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import util.ConvertType
import java.lang.IllegalArgumentException

class CoordinateGeneratorRequestTest : FreeSpec({

    "of" - {

        "정수로 변환할 수 없는 문자열이 입력될 시 IllegalArgumentException" {
            val landMineMapRequestDto = LandMineMapRequest.of("2", "3")
            val exception = shouldThrow<IllegalArgumentException> {
                CoordinateGeneratorRequest.of(landMineMapRequestDto, "test")
            }
            exception.message shouldBe ConvertType.CANNOT_CONVERT_INT
        }

        "0 또는 0이하의 수가 입력될 시 IllegalArgumentException" {
            val landMineMapRequestDto = LandMineMapRequest.of("2", "3")
            val exception = shouldThrow<IllegalArgumentException> {
                CoordinateGeneratorRequest.of(landMineMapRequestDto, "-2")
            }
            exception.message shouldBe ConvertType.ZERO_NEGATIVE_ERROR
        }

        "입력값이 CoordinateGeneratorRequest 로 변환되어야한다." {
            val landMineMapRequestDto = LandMineMapRequest.of("1", "1")
            val coordinateGeneratorRequest = CoordinateGeneratorRequest.of(landMineMapRequestDto, "1")

            coordinateGeneratorRequest.coordinates shouldBe listOf(Coordinate(CoordinatePoint(1), CoordinatePoint(1)))
            coordinateGeneratorRequest.landMine shouldBe 1
        }
    }
})
