package org.example.tutorial.hello.osgi.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.example.tutorial.hello.osgi.service.api.SayingTeller;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class SayingTellerImpl implements SayingTeller, BundleActivator {

    private List<String> sayingLines;

    private ServiceReference<SayingTeller> serviceRef;
    private ServiceRegistration<SayingTeller> serviceReg;
    private Random random;

    public SayingTellerImpl() {
        random = new Random();
        sayingLines = new LinkedList<>();

        try (InputStream is = SayingTellerImpl.class.getResourceAsStream("/korean_sayings.txt");
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr)) {
            String line = br.readLine();

            while (line != null) {
                line = line.trim();

                if (!line.isEmpty() && !line.startsWith("#")) {
                    sayingLines.add(line);
                }

                line = br.readLine();
            }
        }
        catch (IOException e) {
            System.out.println("Error in reading /korean_sayings.txt. " + e);
        }
    }

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Registering SayingTeller service.");
        serviceReg = context.registerService(SayingTeller.class, new SayingTellerImpl(),
                new Hashtable<String, String>());
        serviceRef = serviceReg.getReference();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Unregistering SayingTeller service.");
        serviceReg.unregister();
    }

    @Override
    public String tellSaying() {
        final int index = random.nextInt(sayingLines.size());
        return sayingLines.get(index);
    }

}
