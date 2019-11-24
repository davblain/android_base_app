package com.gemini.base.sample

import core.BaseController
import android.view.View

import com.gemini.base.R
import com.gemini.base.sample.test.TestController
import core.utils.TabRouter
import core.utils.TabScreen

import kotlinx.android.synthetic.main.root_flow.view.*



class TestFlow : BaseController(R.layout.root_flow) {
    private lateinit var homeScreen: TabScreen
    private lateinit var moviesScreen: TabScreen
    private lateinit var favoritesScreen: TabScreen
    private lateinit var tabRouter: TabRouter
    override fun initializeView(view: View) = with(view) {
        homeScreen = TabScreen(TestController())
        moviesScreen = TabScreen(TestController2())
        favoritesScreen = TabScreen(TestController3())
        tabRouter = TabRouter(
            controller = this@TestFlow,
            navLayout = tabNavigation,
            startTabScreen = homeScreen
        )
        tabRouter.bindBottomNavigation(bottom_navigation) {
            homeScreen to R.id.first_screen
            moviesScreen to R.id.second_screen
            favoritesScreen to R.id.third_screen
        }
    }


    override fun onAttach(view: View) {
        super.onAttach(view)
    }
    override fun handleBack(): Boolean {
        return tabRouter.handleBack()
    }
}