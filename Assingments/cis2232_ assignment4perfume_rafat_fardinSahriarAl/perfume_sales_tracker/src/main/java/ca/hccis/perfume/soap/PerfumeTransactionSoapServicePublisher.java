package ca.hccis.perfume.soap;
import javax.xml.ws.Endpoint;
public class PerfumeTransactionSoapServicePublisher {

    public static void main(String[] args) {

        Endpoint.publish(
                "http://0.0.0.0:8083/perfumetransactionsoapservice",
                new PerfumeTransactionSoapServiceImpl()
        );

        System.out.println("SOAP available at:");
        System.out.println("http://localhost:8083/perfumetransactionsoapservice?wsdl");
    }
}
