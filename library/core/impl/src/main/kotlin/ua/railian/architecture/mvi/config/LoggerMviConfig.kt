package ua.railian.architecture.mvi.config

import ua.railian.architecture.mvi.MviModel
import ua.railian.architecture.mvi.log.Category
import ua.railian.architecture.mvi.log.KMLogging
import ua.railian.architecture.mvi.log.Level
import ua.railian.architecture.mvi.log.Output
import ua.railian.architecture.mvi.pipeline.PipelineId

public object LoggerMviConfigDefaults : LoggerMviConfig {
    override val tag: MviModel<*, *>.() -> String? = { this::class.simpleName }
    override val level: Level = Level.Verbose
    override val categories: Set<Category> = Category.entries.toSet()
    override val filter: (pipelineId: PipelineId) -> Boolean = { true }
    override val outputs: Set<Output> = setOf(Output.KMLogging)
}

public class LoggerMviConfigEditor(
    source: LoggerMviConfig = LoggerMviConfigDefaults,
) : LoggerMviConfig.Editor {
    override var tag: MviModel<*, *>.() -> String? = source.tag
    override var level: Level = source.level
    override var categories: Set<Category> = source.categories
    override var filter: (pipelineId: PipelineId) -> Boolean = source.filter
    override var outputs: Set<Output> = source.outputs
}

public fun LoggerMviConfig(
    editor: LoggerMviConfig.Editor.() -> Unit = {},
): LoggerMviConfig {
    return LoggerMviConfigEditor().apply(editor)
}