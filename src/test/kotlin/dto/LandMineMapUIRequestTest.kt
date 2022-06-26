package dto

import domain.Coordinate
import domain.LandMineRandomMap
import domain.Line
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import util.CoordinateGeneratorFake
import util.PositiveInt

class LandMineMapUIRequestTest : FreeSpec({

    "입력값이 LandMineMapUIRequest 로 변환되어야한다." {
        val x = PositiveInt(1)
        val y = PositiveInt(1)
        val landMineRandomMap = LandMineRandomMap(
            LandMineMapRequest.of("1", "1"),
            CoordinateGeneratorFake(listOf(Coordinate(x, y)))
        )

        val landMineMapUIRequest = LandMineMapUIRequest(landMineRandomMap)
        landMineMapUIRequest.mapCoordinates shouldBe listOf(Line(listOf(Coordinate(x, y))))
        landMineMapUIRequest.landMineCoordinates shouldBe listOf(Coordinate(x, y))
    }
})
