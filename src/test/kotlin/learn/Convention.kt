package learn

import io.kotest.core.spec.style.StringSpec

class Convention : StringSpec({

    "코틀린 클래스 레이아웃을 학습한다" {
        print("https://kotlinlang.org/docs/coding-conventions.html#function-names")
        val layoutSample = LayoutSample(
            """
            The contents of a class should go in the following order:
              1. Property declarations and initializer blocks
              2. Secondary constructors
              3. Method declarations
              4. Companion object
        """.trimIndent()
        )
        println(layoutSample.funny())
    }

})

class LayoutSample(
    val attribute: String,
) {
    var attribute2: String = "LayoutSample = "

    constructor(value: Int) : this(value.toString())

    fun funny(): String {
        return this.attribute2 + this.attribute
    }

    companion object {
        private const val ATTRIBUTE3 = "helloworld"

        fun some(): LayoutSample {
            return LayoutSample("hi")
        }
    }
}
