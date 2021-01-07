package id.kudzoza.feature.dummy.screen.onboard

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.MediatorLiveData
import id.kudzoza.feature.dummy.R
import id.kudzoza.feature.dummy.databinding.ActivityOnboardBinding
import id.kudzoza.lib.module.HomeModule
import id.kudzoza.lib.mvvm.BaseActivity
import id.kudzoza.lib.mvvm.util.notNullIsVisible
import id.kudzoza.lib.mvvm.util.nullIsVisible
import id.kudzoza.lib.mvvm.util.watch

/**
 * Created by Kudzoza
 * on 18/12/2020
 **/

class OnBoardActivity : BaseActivity<ActivityOnboardBinding, OnBoardVM>(
    ActivityOnboardBinding::inflate
) {

    private val labelMediator = MediatorLiveData<OnBoardLabel>().apply {
        value = OnBoardLabel()
    }

    override fun onViewReady() {
        binding.toggleNullable.setOnClickListener { vm.toggleNullable() }

        binding.addResource.setOnClickListener { vm.incResource() }
        binding.addDone.setOnClickListener { vm.incDone() }

        binding.goHome.setOnClickListener {
            openModule(
                HomeModule.get(),
                HomeModule::open,
                HomeModule.homeData("061097")
            )
        }
    }

    override fun observeLiveData() {
        labelMediator.addSource(vm.source) { onChangeLabelMediator(it, true) }
        labelMediator.addSource(vm.done) { onChangeLabelMediator(it, false) }

        watch(labelMediator) {
            binding.label.text = getString(
                R.string.label_double,
                it.done.toString(),
                it.source.toString()
            )
        }

        watch(vm.nullable){
            binding.accessibleIcon.visibility = it.notNullIsVisible()
            binding.notAccessibleIcon.visibility = it.nullIsVisible()
        }
    }

    private fun onChangeLabelMediator(value: Int, isSource: Boolean) {
        val current = labelMediator.value
        labelMediator.value =
            if (isSource) current?.copy(source = value)
            else current?.copy(done = value)
    }

    override fun getViewModel() = OnBoardVM::class.java

    data class OnBoardLabel(
        val source: Int = 0,
        val done: Int = 0
    )
}