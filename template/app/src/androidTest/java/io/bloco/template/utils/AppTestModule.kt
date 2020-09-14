package io.bloco.template.utils

import android.content.Context
import androidx.preference.PreferenceManager
import com.tfcporciuncula.flow.FlowSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.bloco.template.data.CounterRepository
import kotlinx.coroutines.runBlocking

@Module
@InstallIn(ApplicationComponent::class)
object AppTestModule{

    @Provides
    fun flowSharedPreferences(@ApplicationContext appContext: Context) : FlowSharedPreferences {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext)
        sharedPreferences.edit().clear().commit()
        val flowSharedPreferences =  FlowSharedPreferences(sharedPreferences)

        runBlocking {
            flowSharedPreferences.getInt(CounterRepository.COUNTER_KEY, defaultValue = 1).setAndCommit(1)
        }
        return  flowSharedPreferences
    }
}