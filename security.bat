@echo off
setlocal

set MULE_HOME=..\..
set MULE_LIB=%MULE_HOME%\lib
set MULE_BOOT_LIB=%MULE_LIB%\boot
set MULE_OPT_LIB=%MULE_LIB%\opt

set CP=lib\security-client.jar
set CP=%CP%;%MULE_OPT_LIB%\cxf-api-2.3.1.jar
set CP=%CP%;%MULE_OPT_LIB%\cxf-rt-core-2.3.1.jar
set CP=%CP%;%MULE_OPT_LIB%\cxf-common-utilities-2.3.1.jar
set CP=%CP%;%MULE_OPT_LIB%\cxf-rt-frontend-simple-2.3.1.jar
set CP=%CP%;%MULE_OPT_LIB%\cxf-rt-frontend-jaxws-2.3.1.jar
set CP=%CP%;%MULE_OPT_LIB%\cxf-rt-bindings-soap-2.3.1.jar
set CP=%CP%;%MULE_OPT_LIB%\cxf-rt-databinding-jaxb-2.3.1.jar
set CP=%CP%;%MULE_OPT_LIB%\cxf-rt-ws-addr-2.3.1.jar
set CP=%CP%;%MULE_OPT_LIB%\cxf-rt-transports-http-2.3.1.jar
set CP=%CP%;%MULE_OPT_LIB%\cxf-rt-ws-security-2.3.1.jar
set CP=%CP%;%MULE_OPT_LIB%\cxf-tools-common-2.3.1.jar
set CP=%CP%;%MULE_OPT_LIB%\wsdl4j-1.6.2.jar
set CP=%CP%;%MULE_OPT_LIB%\XmlSchema-1.4.7.jar
set CP=%CP%;%MULE_OPT_LIB%\wss4j-1.5.8-osgi.jar
set CP=%CP%;%MULE_OPT_LIB%\xmlsec-1.4.0-osgi.jar
set CP=%CP%;%MULE_LIB%\endorsed\xalan-2.7.1.jar
set CP=%CP%;%MULE_OPT_LIB%\opensaml-1.1b.jar
set CP=%CP%;%MULE_BOOT_LIB%\commons-codec-1.3-osgi.jar
set CP=%CP%;%MULE_BOOT_LIB%\jcl-over-slf4j-1.6.1.jar
set CP=%CP%;%MULE_BOOT_LIB%\log4j-1.2.14.jar
set CP=%CP%;%MULE_BOOT_LIB%\slf4j-log4j12-1.6.1.jar
set CP=%CP%;%MULE_BOOT_LIB%\slf4j-api-1.6.1.jar

java -classpath %CP% com.mulesoft.mule.example.security.SecureClient
