package io.bloco.template.common.di

import dagger.Subcomponent
import io.bloco.template.ui.counter.CounterActivity

@PerActivity
@Subcomponent
interface ActivityComponent {
    // Activities
    fun inject(activity: CounterActivity)
}
