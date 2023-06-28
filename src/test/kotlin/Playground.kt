import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Point
import kotlin.random.Random

class Playground: StringSpec({
    "Random.nextInt" {
        println(Random.nextInt(0, 3))
        println(Random.nextInt(0, 3))
        println(Random.nextInt(0, 3))
        println(Random.nextInt(0, 3))
        println(Random.nextInt(0, 3))
    }
    "List.sublist" {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        println(list)
        println(list.subList(0, 3))
        println(list.subList(3, 6))
        println(list.subList(6, 9))
        println(list)
    }
    "Set" {
        val set = setOf(Point(1, 1), Point(1, 2), Point(1, 1))
        set.size shouldBe  2
    }
})
