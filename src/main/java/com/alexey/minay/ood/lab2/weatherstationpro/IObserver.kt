package com.alexey.minay.ood.lab2.weatherstationpro

interface IObservable<D, T> {
    val types: MutableList<T>
    fun register(priority: Int, observer: IObserver<D, T>)
    fun notify(data: D)
    fun remove(observer: IObserver<D, T>)
}

interface IObserver<D, T> {
    fun update(data: D, type: T)
}

open class Observable<D, T> : IObservable<D, T> {

    override val types: MutableList<T> = mutableListOf()

    private val mObservers = sortedMapOf<Int, MutableList<IObserver<D, T>>>()

    override fun register(priority: Int, observer: IObserver<D, T>) {
        val observers = mObservers[priority] ?: mutableListOf()
        observers.add(observer)
        mObservers[priority] = observers
    }

    override fun notify(data: D) {
        mObservers
            .flatMap { it.value }
            .forEach { observer ->
                types.forEach { type ->
                    observer.update(data, type)
                }
            }
    }

    override fun remove(observer: IObserver<D, T>) {
        mObservers.values.forEach { it.remove(observer) }
    }

}