package domain

import dto.LandMineMapRequest
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import util.CoordinateGeneratorFake

class LandMineRandomMapTest : FreeSpec({

    "지뢰의 랜덤한 좌표 목록이 있어야한다." {
        val coordinateGenerator = CoordinateGeneratorFake(listOf(Coordinate(1, 1)))
        val landMineMapRequestDto = LandMineMapRequest.of("1", "3")
        val landMineRandomMap = LandMineRandomMap(landMineMapRequestDto, coordinateGenerator)

        landMineRandomMap.landMineCoordinates shouldBe listOf(Coordinate(1, 1))
    }
})
