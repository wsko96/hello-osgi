package org.example.tutorial.hello.osgi.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class HelloOsgiActivator implements BundleActivator {

    public void start(BundleContext ctx) {
        System.out.println("Hello, OSGi!");
    }

    public void stop(BundleContext bundleContext) {
        System.out.println("Goodbye, OSGi!");
    }
}
