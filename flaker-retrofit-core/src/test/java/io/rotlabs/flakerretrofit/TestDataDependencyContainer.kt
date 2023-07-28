package io.rotlabs.flakerretrofit

import io.rotlabs.FlakerDataDependencyContainer
import io.rotlabs.flakedomain.networkrequest.NetworkRequest
import io.rotlabs.flakerdb.networkrequest.data.NetworkRequestRepo
import io.rotlabs.flakerprefs.PrefDataStore
import io.rotlabs.flakerprefs.dto.FlakerPrefs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow

class TestDataDependencyContainer : FlakerDataDependencyContainer(FakeContext()) {

    private val fakeNetworkRequestRepo = object : NetworkRequestRepo {
        override fun selectAll(): List<NetworkRequest> {
            return emptyList()
        }

        override fun insert(networkRequest: NetworkRequest) = Unit

        override fun observeAll(): Flow<List<NetworkRequest>> {
            return emptyFlow()
        }
    }

    private val fakePrefDataStore = object : PrefDataStore {

        private val prefFlow = MutableStateFlow(
            FlakerPrefs(
                shouldIntercept = false,
                delay = 0,
                failPercent = 0,
                variancePercent = 0
            )
        )

        override fun getPrefs(): Flow<FlakerPrefs> = flow {
            emit(prefFlow.value)
            emitAll(prefFlow)
        }

        override suspend fun savePrefs(flakerPrefs: FlakerPrefs) {
            prefFlow.emit(flakerPrefs)
        }
    }

    override val networkRequestRepo: NetworkRequestRepo
        get() = fakeNetworkRequestRepo

    override val prefsDataStore: PrefDataStore
        get() = fakePrefDataStore
}
