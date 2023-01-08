package minesweeper.domain.coordinate

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import minesweeper.domain.Position
import minesweeper.domain.RandomMineGenerator

internal class MineCountingCoordinateSystemDecoratorTest : FreeSpec({

    "지뢰 개수 시스템은" - {

        val height = 10
        val width = 10
        val mineCount = 10

        val baseCoordinateSystem = BaseCoordinateSystem(height = height, width = width)
        val mineGenerator = RandomMineGenerator

        val mineCoordinateSystemDecorator = MineCoordinateSystemDecorator(baseCoordinateSystem = baseCoordinateSystem, mineGenerator = mineGenerator, mineCount = mineCount)
        val mineCountingCoordinateSystemDecorator = MineCountingCoordinateSystemDecorator(mineCoordinateSystem = mineCoordinateSystemDecorator)

        "지뢰 개수 목록을 확인할 수 있다" {
            mineCountingCoordinateSystemDecorator.mineCountNumbers.shouldNotBeNull()
        }

        "근처 좌표를 확인할 수 있다" - {

            val minX = 0
            val minY = 0
            val maxX = width - 1
            val maxY = height - 1

            "좌표 모서리 위치의 근처 좌표는 3개이다" - {

                "0, 0 좌표" {
                    val cornerPosition = Position.of(minX, minY)
                    val nearPositions = mineCountingCoordinateSystemDecorator.getNearPosition(cornerPosition)
                    nearPositions.size shouldBe 3
                }

                "최소 x좌표, 최대 y좌표" {
                    val cornerPosition = Position.of(minX, maxY)
                    val nearPositions = mineCountingCoordinateSystemDecorator.getNearPosition(cornerPosition)
                    nearPositions.size shouldBe 3
                }

                "최대 x좌표, 최소 y좌표" {
                    val cornerPosition = Position.of(maxX, minY)
                    val nearPositions = mineCountingCoordinateSystemDecorator.getNearPosition(cornerPosition)
                    nearPositions.size shouldBe 3
                }

                "최대 x좌표, 최대 y좌표" {
                    val cornerPosition = Position.of(maxX, maxY)
                    val nearPositions = mineCountingCoordinateSystemDecorator.getNearPosition(cornerPosition)
                    nearPositions.size shouldBe 3
                }
            }

            "좌표 변에 위치의 근처 좌표는 5개이다" - {

                "상단 변 근처 좌표" {
                    val edgePosition = Position.of(minX, minY + 1)
                    val nearPositions = mineCountingCoordinateSystemDecorator.getNearPosition(edgePosition)
                    nearPositions.size shouldBe 5
                }

                "하단 변 근처 좌표" {
                    val edgePosition = Position.of(maxX, minY + 1)
                    val nearPositions = mineCountingCoordinateSystemDecorator.getNearPosition(edgePosition)
                    nearPositions.size shouldBe 5
                }

                "우측 변 근처 좌표" {
                    val edgePosition = Position.of(minX + 1, maxY)
                    val nearPositions = mineCountingCoordinateSystemDecorator.getNearPosition(edgePosition)
                    nearPositions.size shouldBe 5
                }

                "좌측 변 근처 좌표" {
                    val edgePosition = Position.of(minX + 1, minY)
                    val nearPositions = mineCountingCoordinateSystemDecorator.getNearPosition(edgePosition)
                    nearPositions.size shouldBe 5
                }
            }

            "일반 좌표의 근처 좌표는 8개이다" {
                val standardPosition = Position.of(minX + 1, minY + 1)
                val nearPositions = mineCountingCoordinateSystemDecorator.getNearPosition(standardPosition)
                nearPositions.size shouldBe 8
            }
        }
    }
})
