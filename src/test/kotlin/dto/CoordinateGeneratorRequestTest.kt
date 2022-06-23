package dto

import domain.Coordinate
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import util.ConvertType
import java.lang.IllegalArgumentException

class CoordinateGeneratorRequestTest : FreeSpec({

    "of" - {

        "정수로 변환할 수 없는 문자열이 입력될 시 IllegalArgumentException" {
            val exception = shouldThrow<IllegalArgumentException> {
                CoordinateGeneratorRequest.of(listOf(1, 2, 3), listOf(1, 2, 3), "test")
            }
            exception.message shouldBe ConvertType.CANNOT_CONVERT_INT
        }

        "0 또는 0이하의 수가 입력될 시 IllegalArgumentException" {
            val exception = shouldThrow<IllegalArgumentException> {
                CoordinateGeneratorRequest.of(listOf(1, 2, 3), listOf(1, 2, 3), "-2")
            }
            exception.message shouldBe ConvertType.ZERO_NEGATIVE_ERROR
        }

        "입력값이 CoordinateGeneratorRequest 로 변환되어야한다." {
            val coordinateGeneratorRequest = CoordinateGeneratorRequest.of(listOf(1), listOf(1), "1")

            coordinateGeneratorRequest.coordinates shouldBe listOf(Coordinate(1, 1))
            coordinateGeneratorRequest.landMine shouldBe 1
        }
    }
})
