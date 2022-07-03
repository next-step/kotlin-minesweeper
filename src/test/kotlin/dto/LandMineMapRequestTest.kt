package dto

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import util.ConvertType

class LandMineMapRequestTest : FreeSpec({

    "of" - {

        "정수로 변환할 수 없는 문자열이 입력될 시 IllegalArgumentException" {

            val exception = shouldThrow<java.lang.IllegalArgumentException> {
                LandMineMapRequest.of("test", "1", "1")
            }
            exception.message shouldBe ConvertType.CANNOT_CONVERT_INT
        }

        "0 또는 0이하의 수가 입력될 시 IllegalArgumentException" {
            val exception = shouldThrow<java.lang.IllegalArgumentException> {
                LandMineMapRequest.of("1", "1", "-3")
            }
            exception.message shouldBe ConvertType.ZERO_NEGATIVE_ERROR
        }

        "입력값이 LandMineMapRequest 로 변환되어야한다." {
            val landMineMapRequestDto = LandMineMapRequest.of("2", "3", "1")
            landMineMapRequestDto.height.size shouldBe 2
            landMineMapRequestDto.width.size shouldBe 3
        }
    }
})
