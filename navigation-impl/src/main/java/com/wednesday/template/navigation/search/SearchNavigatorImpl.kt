package com.wednesday.template.navigation.search

import com.wednesday.template.navigation.BaseNavigator

class SearchNavigatorImpl(
    private val baseNavigator: BaseNavigator
) : SearchNavigator {

    override fun back() {
        baseNavigator.back()
    }
}
