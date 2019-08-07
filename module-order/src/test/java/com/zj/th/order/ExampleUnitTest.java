package com.zj.th.order;

import org.junit.Test;

import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testRxJava() {
        Observable.just(1, 2, 3)
                .doOnSubscribe(disposable -> System.out.println("doOnSubscribe:before doOnNext"))
                .doOnComplete(() -> System.out.println("doOnCompleted"))
                .doFinally(() -> System.out.println("doFinally:before doOnNext"))
                .doAfterTerminate(() -> System.out.println("doAfterTerminate:before doOnNext"))
                .doFinally(() -> System.out.println("doFinally:before doOnNext"))
                .doOnNext(num -> System.out.println("doOnNext:" + num))
                .doOnNext(num -> {
                    if (num == 3) {
                        throw new Exception("error");
                    }
                })
                .doOnError(throwable -> System.out.println("doOnError:" + throwable.getMessage()))
                .doFinally(() -> System.out.println("doFinally:after doOnNext"))
                .doAfterTerminate(() -> System.out.println("doAfterTerminate:after doOnNext"))
                .doFinally(() -> System.out.println("doFinally:after doOnNext"))
                .doOnSubscribe(disposable -> System.out.println("doOnSubscribe:after doOnNext"))
                .subscribe(num -> {
                            System.out.println("subscribe:" + num);
                            assertEquals(1, 1);
                            if (num == 3) {
                                //throw new Exception("error");
                            }
                        },
                        throwable -> {
                            System.out.println("subscribe:" + throwable.getMessage());
                            throw new Exception("error");
                        });
    }
}