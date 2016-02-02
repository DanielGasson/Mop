package mop.main.java.backend.configuration;

import mop.main.java.backend.utilities.Log;

import org.apache.logging.log4j.Logger;

public class AppConfig {

    private static final Logger log = Log.getLog(AppConfig.class);

    // database
    private final String username;
    private final String password;
    private final String defaultSchema;

    // playlist
    private final int id;
    private final String name;

    private AppConfig(Builder builder) {

        this.username = builder.username == null ? "" : builder.username;
        this.password = builder.password == null ? "" : builder.password;
        this.defaultSchema = builder.defaultSchema;
        this.id = builder.id;
        this.name = builder.name == null ? "" : builder.name;
    }

    public String getUsername() {

        return username;
    }

    public String getPassword() {

        return password;
    }

    public String getDefaultSchema() {

        return defaultSchema;
    }

    public int getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public static class Builder {

        private String username, password, defaultSchema, name;
        private int id;

        public Builder username(String username) {

            this.username = username;
            return this;
        }

        public Builder password(String password) {

            this.password = password;
            return this;
        }

        public Builder defaultSchema(String defaultSchema) {

            this.defaultSchema = defaultSchema;
            return this;
        }

        public Builder id(String id) {

            if(id != null) {

                try {

                    this.id = Integer.parseInt(id);
                }
                catch(NumberFormatException ex) {

                    log.error("Error parsing playlist Id '" + id + "' from String.", ex);
                }
            }

            return this;
        }

        public Builder id(int id) {

            this.id = id;
            return this;
        }

        public Builder name(String name) {

            this.name = name;
            return this;
        }

        public AppConfig build() {

            return new AppConfig(this);
        }
    }
}
