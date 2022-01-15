package remote_module

interface RemoteProvider {
    fun provideRemote(): TmdbApi
}