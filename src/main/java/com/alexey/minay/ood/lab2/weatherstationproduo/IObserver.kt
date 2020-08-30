package com.alexey.minay.ood.lab2.weatherstationproduo

interface IObservable<T> {
    fun register(priority: Int, observer: IObserver<T>)
    fun notify(data: T)
    fun remove(observer: IObserver<T>)
}

interface IObserver<T> {
    fun update(data: T, observable: IObservable<T>)
}

open class Observable<T> : IObservable<T> {

    private val mObservers = sortedMapOf<Int, MutableList<IObserver<T>>>()

    override fun register(priority: Int, observer: IObserver<T>) {
        val observers = mObservers[priority] ?: mutableListOf()
        observers.add(observer)
        mObservers[priority] = observers
    }

    override fun notify(data: T) {
        mObservers
                .flatMap { it.value }
                .forEach { it.update(data, this) }
    }

    override fun remove(observer: IObserver<T>) {
        mObservers.values.forEach { it.remove(observer) }
    }

}
