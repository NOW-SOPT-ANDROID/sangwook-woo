package org.sopt.main.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import org.sopt.main.R
import org.sopt.main.const.IntentKey.USER_KEY
import org.sopt.main.databinding.ActivityMainBinding
import org.sopt.main.login.LoginActivity
import org.sopt.main.model.UserModel
import org.sopt.ui.base.BindingActivity
import org.sopt.ui.intent.getParcelable

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it)}) {
    private lateinit var navController: NavController
    private val viewModel by viewModels<MainViewModel>()
    val user by lazy {
        intent.getParcelable(USER_KEY, UserModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        collectState()
        initFragmentContainerView()
    }

    private fun collectState() {
        viewModel.observe(this, state = ::render)
    }

    private fun render(mainState: MainState){
        binding.bnvMain.isVisible = mainState.isBnvBarVisible
    }

    private fun initFragmentContainerView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
        navController = navHostFragment.navController
        navController.graph.setStartDestination(org.sopt.home.R.id.navigation_graph_home)
        binding.bnvMain.setupWithNavController(navController)
        binding.bnvMain.setOnItemReselectedListener { }
        initBnvItemSelectedListener()
        setBottomNavigationVisible()
    }
    private fun initBnvItemSelectedListener() {
        binding.bnvMain.setOnItemSelectedListener {
            if(binding.bnvMain.menu.findItem(it.itemId).isChecked){
                false
            } else {
                navController.apply {
                    popBackStack(org.sopt.home.R.id.navigation_graph_home, false)
                    popBackStack(org.sopt.search.R.id.navigation_graph_search, false)
                    popBackStack(org.sopt.mypage.R.id.navigation_graph_mypage, false)
                    if(it.itemId == R.id.navigation_home) {
                        popBackStack(org.sopt.home.R.id.navigation_graph_home, true)
                    }
                }
                navigateToDestination(it.itemId)
            }
        }
    }

    private fun setBottomNavigationVisible() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (navigationMap.keys.contains(destination.id)){
                viewModel.updateBnvVisible(true)
            } else {
                viewModel.updateBnvVisible(false)
            }
        }
    }
    private fun navigateToDestination(itemId: Int): Boolean {
        return navigationMap[itemId]?.let { destination ->
            navController.navigate(destination)
            true
        } ?: false
    }

    private val navigationMap = mapOf(
        R.id.navigation_home to org.sopt.home.R.id.navigation_graph_home,
        R.id.navigation_search to org.sopt.search.R.id.navigation_graph_search,
        R.id.navigation_mypage to org.sopt.mypage.R.id.navigation_graph_mypage,
    )

    companion object {
        @JvmStatic
        fun newInstance(context: Context) = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }
}