package com.alexey.minay.ood.lab2.weatherstation

interface IObservable<T> {
    fun register(priority: Int, observer: IObserver<T>)
    fun notify(data: T)
    fun remove(observer: IObserver<T>)
}

interface IObserver<T> {
    fun update(data: T)
}

open class Observable<T> : IObservable<T> {

    private val observers = sortedMapOf<Int, IObserver<T>>()

    override fun register(priority: Int, observer: IObserver<T>) {
        observers[priority] = observer
    }

    override fun notify(data: T) {
        observers.forEach { it.value.update(data) }
    }

    override fun remove(observer: IObserver<T>) {
        observers.values.remove(observer)
    }

}
