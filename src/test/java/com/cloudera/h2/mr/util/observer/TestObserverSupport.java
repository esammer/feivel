package com.cloudera.h2.mr.util.observer;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestObserverSupport {

  private ObserverSupport observerSupport;

  @Before
  public void setup() {
    observerSupport = new ObserverSupport();
  }

  @Test
  public void testObserverSupport() {
    Assert.assertNotNull(observerSupport);
    Assert.assertEquals(0, observerSupport.getObservers().size());
  }

  @Test
  public void testDispatchEvent() {
    ObserverEvent<String> event;
    DebuggingObserver observer;

    event = ObserverEvent.withTypeAndSubject("test", "Test event");

    observerSupport.dispatchEvent(event);

    observer = new DebuggingObserver();

    observerSupport.addObserver(observer);

    Assert.assertEquals(1, observerSupport.getObservers().size());

    for (int i = 0; i < 10; i++) {
      observerSupport.dispatchEvent(event);
    }

    Assert.assertEquals(10, observer.getCount());
  }

  private static class DebuggingObserver implements Observer {

    private static final Logger logger = LoggerFactory
        .getLogger(DebuggingObserver.class);

    private long count;

    @Override
    public void onEvent(ObserverEvent<?> event) throws ObserverException {
      count++;

      logger.debug("Received event #" + count + " event:" + event);
    }

    public long getCount() {
      return count;
    }

  }

}
