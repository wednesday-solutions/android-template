package com.wednesday.template.navigation.search

import com.wednesday.template.navigation.Navigator

class SearchNavigatorImpl(
    private val navigator: Navigator
) : SearchNavigator {

    override fun back() {
        navigator.back()
    }
}