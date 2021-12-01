package global.strategy

object ConsoleInputStrategy : InputStrategy {
    override fun execute(): String = readLine() ?: throw IllegalArgumentException()
}
