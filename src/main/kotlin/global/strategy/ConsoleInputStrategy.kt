package global.strategy

import global.exception.ReadLineNullPointerException

object ConsoleInputStrategy : InputStrategy {
    override fun execute(): String = readLine() ?: throw ReadLineNullPointerException()
}
