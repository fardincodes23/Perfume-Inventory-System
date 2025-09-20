package info.hccis.bus.pass.soap;

import javax.xml.ws.Endpoint;

public class BusPassServicePublisher {
    public static void main(String[] args) {
        Endpoint.publish(
          "http://localhost:8083/buspassservice",
           new BusPassServiceImpl());

    }
}