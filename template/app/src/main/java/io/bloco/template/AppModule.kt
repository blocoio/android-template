package io.bloco.template

import android.content.Context
import androidx.preference.PreferenceManager
import com.tfcporciuncula.flow.FlowSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun flowSharedPreferences(@ApplicationContext appContext: Context) =
        FlowSharedPreferences(PreferenceManager.getDefaultSharedPreferences(appContext))

}