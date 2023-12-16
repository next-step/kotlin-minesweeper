package minesweeper.model.board

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.model.point.CoordinateFixture.toCoordinate
import minesweeper.model.vison.impl.VisionTotalCoveringStrategy

class VisionTest : StringSpec({

    "몇개의 Coordinate 들이 가려져있는지 반환해야 한다" {
        val limit = (4 to 4).toBoardLimit()
        val vision = VisionTotalCoveringStrategy.coordinates(limit).toVision()
        vision.coveredCount shouldBe 16
    }

    "입력으로 들어오는 Coordinate 들에 대해서 모두 노출되도록 수정되어야한다" {
        val limit = (4 to 4).toBoardLimit()
        val vision = VisionTotalCoveringStrategy.coordinates(limit).toVision()
        vision.exposeAll(VisionTotalCoveringStrategy.coordinates(limit))

        vision.coveredCount shouldBe 0
    }

    "특정지점이 가려져있는경우 가려져있음을 반환하여야 한다" {
        val limit = (4 to 4).toBoardLimit()
        val vision = VisionTotalCoveringStrategy.coordinates(limit).toVision()
        val point = (0 to 0).toCoordinate()

        vision.isCovered(point) shouldBe true
    }

    "특정지점이 노출 되어있는경우 노출되어있음을 반환 하여야 한다" {
        val limit = (4 to 4).toBoardLimit()
        val vision = Vision(VisionTotalCoveringStrategy.coordinates(limit))
        val point = (0 to 0).toCoordinate()
        vision.exposeAll(setOf(point))

        vision.isCovered(point) shouldBe false
    }
})
