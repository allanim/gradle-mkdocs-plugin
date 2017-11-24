package ru.vyarus.gradle.plugin.mkdocs.task

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.TaskOutcome
import ru.vyarus.gradle.plugin.mkdocs.AbstractKitTest

/**
 * @author Vyacheslav Rusakov
 * @since 23.11.2017
 */
class MkdocksInitTaskKitTest extends AbstractKitTest {

    def "Check site init"() {
        setup:
        build """
            plugins {
                id 'ru.vyarus.mkdocs'
            }
        """

        when: "run task"
        BuildResult result = run('mkdocsInit')

        then: "task successful"
        result.task(':mkdocsInit').outcome == TaskOutcome.SUCCESS
        def yml = file('src/doc/mkdocs.yml')
        yml.exists()
        !yml.text.contains('@')

        then: "all dirs generated"
        file('src/doc/docs/index.md').exists()
        file('src/doc/docs/about/history.md').exists()
        file('src/doc/docs/guide/installation.md').exists()
    }
}