@file:Suppress("UnstableApiUsage")

package io.bloco.template.lintchecks

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.TextFormat
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UMethod

class MissingExcludePreviewAnnotationDetector : Detector(), Detector.UastScanner {

    override fun getApplicableUastTypes(): List<Class<out UElement>> {
        return listOf(UMethod::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler {
        return PreviewMethodElementHandler(context)
    }

    private class PreviewMethodElementHandler(private val context: JavaContext) :
        UElementHandler() {
        override fun visitMethod(node: UMethod) {
            val isPreviewMethod = node.hasAnnotation(COMPOSE_PREVIEW_ANNOTATION)
            val isExcluded = node.hasAnnotation(EXCLUDE_FROM_JACOCO_ANNOTATION)

            if (isPreviewMethod && !isExcluded) {
                context.report(
                    issue = ISSUE_MISSING_EXCLUDE_PREVIEW_ANNOTATION,
                    location = context.getLocation(node),
                    message = ISSUE_MISSING_EXCLUDE_PREVIEW_ANNOTATION.getExplanation(TextFormat.TEXT)
                )
            }
        }
    }

    companion object {
        private const val COMPOSE_PREVIEW_ANNOTATION = "androidx.compose.ui.tooling.preview.Preview"
        private const val EXCLUDE_FROM_JACOCO_ANNOTATION =
            "io.bloco.template.ExcludeFromJacocoGeneratedReport"

        internal val ISSUE_MISSING_EXCLUDE_PREVIEW_ANNOTATION = Issue.create(
            id = "MissingExcludePreviewAnnotation",
            briefDescription = "Jetpack Compose previews should be excluded from JaCoCo Reports.",
            explanation = "Any methods annotated with @Preview should also have @ExcludeFromJacocoReport annotation.",
            category = Category.CUSTOM_LINT_CHECKS,
            severity = Severity.ERROR,
            implementation = Implementation(
                MissingExcludePreviewAnnotationDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            ),
            priority = 5
        )
    }
}
