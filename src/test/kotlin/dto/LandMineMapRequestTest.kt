package dto

import dto.LandMineMapRequest.Companion.CANNOT_CONVERT_INT
import dto.LandMineMapRequest.Companion.ZERO_NEGATIVE_ERROR
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class LandMineMapRequestTest : FreeSpec({

    "of" - {

        "정수로 변환할 수 없는 문자열이 입력될 시 IllegalArgumentException" {

            val exception = shouldThrow<java.lang.IllegalArgumentException> {
                LandMineMapRequest.of("test", "1", "2")
            }
            exception.message shouldBe CANNOT_CONVERT_INT
        }

        "0 또는 0이하의 수가 입력될 시 IllegalArgumentException" {
            val exception = shouldThrow<java.lang.IllegalArgumentException> {
                LandMineMapRequest.of("-1", "1", "2")
            }
            exception.message shouldBe ZERO_NEGATIVE_ERROR
        }

        "입력값이 LandMineMapRequest 로 변환되어야한다." {
            val landMineMapRequestDto = LandMineMapRequest.of("1", "1", "1")
            landMineMapRequestDto.height shouldBe 1
            landMineMapRequestDto.width shouldBe 1
            landMineMapRequestDto.landMine shouldBe 1
        }
    }
})
