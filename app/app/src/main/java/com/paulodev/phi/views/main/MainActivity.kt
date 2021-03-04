package com.paulodev.phi.views.main

import android.app.ActionBar.*
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.LoadState.Error
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulodev.phi.application.PhiAppApplication
import com.paulodev.phi.R
import com.paulodev.phi.databinding.ActivityMainBinding
import com.paulodev.phi.di.modules.factory.ViewModelFactory
import com.paulodev.phi.views.main.adapter.StatementsPageAdapter
import com.paulodev.phi.views.main.vm.BalanceViewModel
import com.paulodev.phi.views.main.vm.StatementsViewModel
import com.paulodev.phi.views.statement_details.StatementDetailsActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ViewModelStoreOwner,
    StatementsPageAdapter.StatementListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding : ActivityMainBinding
    private lateinit var balanceViewModel : BalanceViewModel
    private lateinit var statementsViewModel: StatementsViewModel
    private lateinit var statementsPagingDataAdapter: StatementsPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        (this.application as PhiAppApplication).phiAppComponent.inject(this)

        balanceViewModel = ViewModelProvider(this, viewModelFactory).get(BalanceViewModel::class.java)
        statementsViewModel = ViewModelProvider(this, viewModelFactory).get(StatementsViewModel::class.java)
        statementsPagingDataAdapter = StatementsPageAdapter(StatementsPageAdapter.StatementComparator, this)
        binding.recyclerStatements.adapter = statementsPagingDataAdapter
        binding.recyclerStatements.layoutManager = LinearLayoutManager(this)

        binding.balance = balanceViewModel
        binding.loading = true

        supportActionBar?.apply {
            // show custom title in action bar
            customView = actionBarCustomView()
            displayOptions = DISPLAY_SHOW_CUSTOM

            setDisplayShowHomeEnabled(true)
            setDisplayUseLogoEnabled(true)
        }

        balanceViewModel.loadBalance()

        loadStatements()
    }

    // method generate custom view for action bar title
    private fun actionBarCustomView(): TextView {
        return TextView(this).apply {
            text = resources.getText(R.string.app_title_center)

            val params = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            layoutParams = params

            // center align title
            params.gravity = Gravity.CENTER

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextAppearance(
                    android.R.style.TextAppearance_Material_Widget_ActionBar_Title
                )
            }else{
                // define your own text style
                setTextSize(TypedValue.COMPLEX_UNIT_SP,17F)
                setTypeface(null, Typeface.BOLD)
            }

            // action bar title text color
            setTextColor(Color.parseColor("#202021"))

            // we can set title font family
            typeface = Typeface.SERIF

            // we can underline the title text
            // paintFlags = Paint.UNDERLINE_TEXT_FLAG

            // we can bold italic title text
            setTypeface(typeface,Typeface.BOLD)
        }
    }

    private fun loadStatements() {
        lifecycleScope.launch {
            statementsViewModel.flow.collectLatest { pagingData ->
                statementsPagingDataAdapter.submitData(pagingData)
            }

            statementsPagingDataAdapter.loadStateFlow.collectLatest { loadStates ->
                when(loadStates.refresh) {
                    LoadState.Loading -> binding.loading = true
                    is Error -> binding.loading = false
                    else -> binding.loading = false
                }
            }
        }
    }

    override fun onStatementClick(id: String) {
        Intent(this, StatementDetailsActivity::class.java).apply {
            putExtra(StatementDetailsActivity.statementId, id)
            startActivity(this)
        }
    }
}