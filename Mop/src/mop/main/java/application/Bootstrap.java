package mop.main.java.application;

import org.apache.logging.log4j.Logger;

import mop.main.java.backend.caching.DatabaseCache;
import mop.main.java.backend.configuration.AppConfig;
import mop.main.java.backend.configuration.ConfigurationManager;
import mop.main.java.backend.utilities.File;
import mop.main.java.backend.utilities.Log;
import mop.main.java.common.Constants;
import mop.main.java.common.exceptions.StartupException;
import mop.main.java.common.exceptions.XmlException;
import mop.main.java.database.objectrelationalmapping.helpers.Type;

class Bootstrap {

    private static final Logger log = Log.getLog(Bootstrap.class);

    public void run() throws StartupException {

        this.loadAppConfiguration();
        this.preLoadStaticClasses();
    }

    private void loadAppConfiguration() throws StartupException {

        try {

            ConfigurationManager configurationManager = new ConfigurationManager();

            if(File.exists(Constants.AppConfig.name)) {

                AppConfig config = configurationManager.getAllValues(Constants.AppConfig.name);

                DatabaseCache cache = DatabaseCache.getInstance();
                cache.set(Constants.AppConfigSections.username, config.getUsername());
                cache.set(Constants.AppConfigSections.password, config.getPassword());
                cache.set(Constants.AppConfigSections.defaultSchema, config.getDefaultSchema());
            }
            else {

                configurationManager.createConfiguration(Constants.AppConfig.name);
            }
        }
        catch(XmlException ex) {

            String msg = "Unable to create or load app configuration.";
            log.fatal(msg, ex);
            throw new StartupException(msg);
        }
    }

    private void preLoadStaticClasses() {

        try {

            Class.forName(Type.class.getName(), true, Type.class.getClassLoader());
        }
        catch (ClassNotFoundException ex) {

            log.warn("Application Startup Warning: Unable to pre-load static classes.", ex);
        }
    }
}
