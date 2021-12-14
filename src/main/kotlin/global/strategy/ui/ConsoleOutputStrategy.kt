package global.strategy.ui

object ConsoleOutputStrategy : OutputStrategy {
    override fun execute(message: String): Unit = println(message)
}
