import io.kotest.core.spec.style.StringSpec
import kotlin.random.Random

class Playground : StringSpec({
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

    "testOfRequire" {
        try {
            testOfRequire(5)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
})

fun testOfRequire(value: Int) {
    require(value > 10) { "값은 10보다 커야 합니다." }
    println("Success.")
}
