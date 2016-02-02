package mop.main.java.database.utilities;

import java.util.HashMap;

public class ConnectionProperties {

    private final String driverName, url, userName, password, schema;
    private final HashMap<String, String> additionalProperties;

    private ConnectionProperties(Builder connectionPropertiesBuilder) {

        this.driverName = connectionPropertiesBuilder.driverName;
        this.url = connectionPropertiesBuilder.url;
        this.userName = connectionPropertiesBuilder.userName;
        this.password = connectionPropertiesBuilder.password;
        this.schema = connectionPropertiesBuilder.schema;

        additionalProperties = new HashMap<>();
    }

    public String getDriverName() {

        return driverName;
    }

    public String getUrl() {

        return url;
    }

    public String getUserName() {

        return userName;
    }

    public String getPassword() {

        return password;
    }

    public String getSchema() {

        return schema;
    }

    public void addProperty(String key, String value) {

        additionalProperties.put(key, value);
    }

    public HashMap<String, String> getAllProperties() {

        return additionalProperties;
    }

    public static class Builder {

        private String driverName, url, userName, password, schema;

        public Builder driverName(String driverName) {

            this.driverName = driverName;
            return this;
        }

        public Builder url(String url) {

            this.url = url;
            return this;
        }

        public Builder userName(String userName) {

            this.userName = userName;
            return this;
        }

        public Builder password(String password) {

            this.password = password;
            return this;
        }

        public Builder schema(String schema) {

            this.schema = schema;
            return this;
        }

        public ConnectionProperties build() {

            return new ConnectionProperties(this);
        }
    }
}
