package domain

import dto.LandMineMapRequest
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import util.CoordinateGeneratorFake

class LandMineRandomMapTest : FreeSpec({

    "지뢰의 랜덤한 좌표 목록이 있어야한다." {
        val x = CoordinatePoint(1)
        val y = CoordinatePoint(1)
        val coordinateGenerator = CoordinateGeneratorFake(listOf(Coordinate(x, y)))
        val landMineMapRequestDto = LandMineMapRequest.of("1", "3", "1")
        val landMineRandomMap = LandMineRandomMap(
            landMineMapRequestDto.xCoordinatePoints,
            landMineMapRequestDto.yCoordinatePoints,
            coordinateGenerator
        )

        landMineRandomMap.landMineCoordinates shouldBe listOf(Coordinate(x, y))
    }
})
