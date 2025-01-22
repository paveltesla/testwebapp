package com.example.services;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }
    public ServiceFactory getServiceFactory() {
        return new ServiceFactory();
    }
}
