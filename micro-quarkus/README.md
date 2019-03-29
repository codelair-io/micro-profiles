# MicroProfile Quarkus

Compile and install using `mvn clean install`  
To run a live-coding environment `mvn compile quarkus:dev` 

## Using SmallRye Health in a native image 
Will just not work:
https://github.com/quarkusio/quarkus/issues/1482  
https://github.com/quarkusio/quarkus/pull/1521  
*Should be fixed as of (2019-03-15)*

## GraalVM Java 11 Support

GraalVM Java 11 support: https://github.com/oracle/graal/issues/651

However, Java 11 supports GraalVM Compiler by using `-XX:+UnlockExperimentalVMOptions -XX:+UseJVMCICompiler`
