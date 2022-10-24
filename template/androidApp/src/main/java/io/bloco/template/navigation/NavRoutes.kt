package io.bloco.template.navigation

sealed class NavRoutes(internal val route: String) {
    object List : NavRoutes("/")
    object Details : NavRoutes("details/{id}") {
        /**
         * Key for String Book Id
         */
        const val Id: String = "id"

        /**
         * Details takes 1 argument, BookId: String
         * @param args 1 BookId as String
         */
        override fun navigationRoute(vararg args: Any): String {
            return route.replace(Id.toRouteArg(), args.first() as String)
        }
    }

    open fun navigationRoute(vararg args: Any): String {
        return route
    }
}

private fun String.toRouteArg() = "{$this}"