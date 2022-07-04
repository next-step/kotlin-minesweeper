package dto

import domain.Coordinate
import domain.CoordinatePoint
import domain.LandMineRandomMap
import domain.Line
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import util.CoordinateGeneratorFake

class LandMineMapUIRequestTest : FreeSpec({

    "입력값이 LandMineMapUIRequest 로 변환되어야한다." {
        val x = CoordinatePoint(1)
        val y = CoordinatePoint(1)
        val landMineMapRequestDto = LandMineMapRequest.of("1", "1", "1")
        val landMineRandomMap = LandMineRandomMap(
            landMineMapRequestDto.xCoordinatePoints,
            landMineMapRequestDto.yCoordinatePoints,
            CoordinateGeneratorFake(listOf(Coordinate(x, y)))
        )

        val landMineMapUIRequest = LandMineMapUIRequest(landMineRandomMap)
        landMineMapUIRequest.mapCoordinates shouldBe listOf(Line(listOf(-1)))
        landMineMapUIRequest.landMineCoordinates shouldBe listOf(Coordinate(x, y))
    }
})
