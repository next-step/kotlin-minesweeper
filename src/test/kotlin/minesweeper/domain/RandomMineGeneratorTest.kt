package minesweeper.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldNotBeIn

class RandomMineGeneratorTest : FreeSpec({
    "정해진 범위안에서 랜덤으로 지뢰의 위치를 결정짓는다" {

        val height = 10
        val width = 10

        val mine = RandomMineGenerator.generate(height = height, width = width)
        mine.position.x shouldNotBeIn arrayListOf(0 until width)
        mine.position.y shouldNotBeIn arrayListOf(0 until height)
    }
})
