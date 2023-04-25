import kotlinx.coroutines.*

//suspend fun <A, B> Collection<A>.asyncMap(f: suspend (A) -> B): Collection<B> = coroutineScope {
//    map { async { f(it) } }.map { it.await() }
//}
//
//suspend fun <A, B : Any> Collection<A>.asyncMapNotNull(f: suspend (A) -> B?): Collection<B> = coroutineScope {
//    map { async { f(it) } }.map { it.await() }
//}.filterNotNull()


fun <A, B> Collection<A>.asyncMap(f: (A) -> B): Collection<B> = runBlocking {
    map { async { f(it) } }.map { it.await() }
}


fun <A, B> Collection<A>.asyncMapNotNull(f: (A) -> B?): Collection<B> = runBlocking {
    map { async { f(it) } }.map { it.await() }
}.filterNotNull()

//val scope = CoroutineScope(Dispatchers.IO)
//val scope = CoroutineScope(newFixedThreadPoolContext(10, "synchronizationPool"))