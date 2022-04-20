package com.axonactive.workshop.order.service.event;

public interface EventService<T> {

    public void writeEvent(T model);

}
