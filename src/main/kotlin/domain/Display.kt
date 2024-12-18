package domain

enum class Display(val value: String) {
    EMPTY("C"),
    MINECELL("*"),
    ;

    override fun toString(): String {
        return value
    }
}
