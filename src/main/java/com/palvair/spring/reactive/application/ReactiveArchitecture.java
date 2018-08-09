package com.palvair.spring.reactive.application;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReactiveArchitecture {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReactiveArchitecture.class);

    public static void main(String[] args) {

        new CustomPublisher().subscribe(new CustomSubscriber());
    }

    public static class CustomPublisher implements Publisher<String> {

        @Override
        public void subscribe(Subscriber<? super String> subscriber) {
            final Subscription subscription = new CustomSubscription(subscriber);
            subscriber.onSubscribe(subscription);
            subscription.request(1);
        }

        private class CustomSubscription implements Subscription {

            private Subscriber<String> subscriber;

            CustomSubscription(final Subscriber subscriber) {
                this.subscriber = subscriber;
            }

            int nbElements = 0;

            @Override
            public void request(long n) {
                nbElements++;
                if (nbElements <= 100) {
                    subscriber.onNext(String.valueOf(Math.random()));
                } else {
                    subscriber.onComplete();
                }
            }

            @Override
            public void cancel() {

            }
        }
    }

    public static class CustomSubscriber implements Subscriber<String> {

        private Subscription subscription;
        private int nbElementsReceived = 0;

        @Override
        public void onSubscribe(Subscription s) {
            this.subscription = s;
        }

        @Override
        public void onNext(String s) {
            LOGGER.debug("Réception de  {}", s);
            nbElementsReceived++;
            subscription.request(1);
        }

        @Override
        public void onError(Throwable t) {
            LOGGER.error("Erreur ", t);
        }

        @Override
        public void onComplete() {
            LOGGER.debug("Complete. Nombre d'éléments reçus = {}", nbElementsReceived);
        }
    }
}
