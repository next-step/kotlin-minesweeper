package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import minesweeper.infrastructrue.CustomSpotGenerator

class FieldTest : StringSpec({
    "필드를 생성할 수 있다." {
        val height = 3
        val width = 4
        val minePositions = setOf(Coordinate(0, 0), Coordinate(1, 2), Coordinate(3, 2))
        val mineCount = MineCount(minePositions.size)
        val fieldInfo = FieldInfo(FieldHeight(height), FieldWidth(width))
        val spotGenerator = CustomSpotGenerator(minePositions)

        val field = Field(fieldInfo, mineCount, spotGenerator)

        field.lines.size shouldBe height

        field.lines.forEach { line ->
            line.spots.size shouldBe width
        }

        (0 until width).forEach { x ->
            (0 until height).forEach { y ->
                if (minePositions.contains(Coordinate(x, y))) {
                    val mineSpot = field.lines[y].spots[x]
                    mineSpot.shouldBeInstanceOf<MineSpot>()
                    mineSpot.coordinate shouldBe Coordinate(x, y)
                } else {
                    val safeSpot = field.lines[y].spots[x]
                    safeSpot.shouldBeInstanceOf<SafeSpot>()
                    safeSpot.coordinate shouldBe Coordinate(x, y)
                }
            }
        }
    }

    /*
     * 1 0 0
     2 2 2 1
     1 * 2 *
     */
    "필드의 각 SafeSpot에 대해 주변에 있는 지뢰의 개수를 계산한다." {
        forAll(
            row(0, 1, 2),
            row(3, 0, 0),
            row(2, 2, 2),
        ) { x, y, expected ->
            val height = 3
            val width = 4
            val minePositions = setOf(Coordinate(0, 0), Coordinate(1, 2), Coordinate(3, 2))
            val mineCount = MineCount(minePositions.size)
            val fieldInfo = FieldInfo(FieldHeight(height), FieldWidth(width))
            val spotGenerator = CustomSpotGenerator(minePositions)

            val field = Field(fieldInfo, mineCount, spotGenerator)

            val spot = field.lines[y].spots[x] as SafeSpot
            spot.nearbyMineCount shouldBe expected
        }
    }
})
