package learn

import io.kotest.core.spec.style.StringSpec

class 이차원지도생성 : StringSpec({

    "안녕" {
        println("ho")
        val mapHeight = 10
        val mapWidth = 10
        (0 until  mapHeight).forEach { row ->
            run {
                (0 until mapWidth).forEach { col ->
                    run {
                        val count = mapHeight*row + col +1
                        println("count=[$count] / row=[$row] / col=[$col]")

                    }
                }
            }
        }
    }
})
