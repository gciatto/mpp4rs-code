import io.github.gciatto.kt.mpp.Plugins
import io.github.gciatto.kt.mpp.ProjectType
import io.github.gciatto.kt.mpp.log
import io.github.gciatto.kt.mpp.nodeVersion

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gitSemVer)
    alias(libs.plugins.ktMpp.multiProjectHelper)
}

group = "io.github.gciatto"

gitSemVer {
    excludeLightweightTags()
    assignGitSemanticVersion()
}

log("version: $version", LogLevel.LIFECYCLE)

multiProjectHelper {
    defaultProjectType = ProjectType.KOTLIN // default project type for all projects which are not explicitly marked

    ktProjects = setOf(project(":csv-core"))
    jvmProjects = emptySet()
    jsProjects = emptySet()
    otherProjects = setOf(project(":csv-python"))

    val baseProjectTemplate = buildSet {
        add(Plugins.documentation)
        add(Plugins.linter)
        add(Plugins.bugFinder)
        add(Plugins.versions)
    }

    ktProjectTemplate = buildSet {
        addAll(baseProjectTemplate)
        add(Plugins.multiplatform)
    }

    jvmProjectTemplate = buildSet {
        addAll(baseProjectTemplate)
        add(Plugins.jvmOnly)
    }

    jsProjectTemplate = buildSet {
        addAll(baseProjectTemplate)
        add(Plugins.jsOnly)
    }

    otherProjectTemplate = buildSet {
        add(Plugins.versions)
    }

    applyProjectTemplates()
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

project.findProperty("nodeVersion")?.toString()?.takeIf { it.isNotBlank() }?.let {
    nodeVersion(it)
    log("override NodeJS version: $it", LogLevel.LIFECYCLE)
}

afterEvaluate {
    subprojects {
        version = rootProject.version
    }
}
