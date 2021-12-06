package global.strategy

object ConsoleOutputStrategy : OutputStrategy {
    override fun execute(message: String): Unit = println(message)
}
