package com.masteringselenium.tests;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyServer {

    private Server jettyServer;

    public JettyServer(int port) throws Exception {
        String resourceBasePath = System.getProperty("resourceBase");
        jettyServer = new Server(port);
        WebAppContext webApplication = new WebAppContext();
        webApplication.setResourceBase(resourceBasePath);
        jettyServer.setHandler(webApplication);
        jettyServer.start();
    }

    public void stopJettyServer() throws Exception {
        jettyServer.stop();
    }
}