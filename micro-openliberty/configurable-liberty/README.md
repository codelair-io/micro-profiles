# MicroProfile Config 1.3

### Default ConfigSources  
* System properties (default ordinal=400).
* Environment variables (default ordinal=300).
* Each property file META-INF/microprofile-config.properties found on the
  classpath. (default ordinal = 100).

The app can be run as is, which will utilize the packaged `META-INF/microprofile-config.properties`. 
Alternatively, system properties or environment variables can be added to overwrite our property configuration.  