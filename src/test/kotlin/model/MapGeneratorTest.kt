package model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*

class MapGeneratorTest: BehaviorSpec({
    Given("맵 제너레이터로 생성된 맵이"){
        When("높이 10 너비 10 지뢰 3일때"){
            val gameMap = MapGenerator.generateMap(10, 10, 3)
            Then("지뢰가 3개다"){
                gameMap.map.flatten().count{
                    it.isMine
                } shouldBe 3
            }
            Then("높이가 10이다"){
                gameMap.map.size shouldBe 10
            }
            Then("높이가 10이다"){
                gameMap.map.first().size shouldBe 10
            }
        }
    }
})
