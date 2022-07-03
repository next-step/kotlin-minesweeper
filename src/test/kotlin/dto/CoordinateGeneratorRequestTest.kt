package dto

import domain.CoordinateGenerator
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.types.shouldBeInstanceOf

class CoordinateGeneratorRequestTest : FreeSpec({

    "of" - {

        "CoordinateGenerator 가 반환되어야한다." {
            val landMineMapRequestDto = LandMineMapRequest.of("1", "1", "1")
            val coordinateGeneratorRequest = CoordinateGeneratorRequest.of(landMineMapRequestDto)

            coordinateGeneratorRequest.shouldBeInstanceOf<CoordinateGenerator>()
        }
    }
})
