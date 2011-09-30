/*
 * $Id: SecurityTestCase.java 18408 2010-12-09 19:44:58Z travis.carlson $
 * --------------------------------------------------------------------------------------
 *
 * (c) 2003-2010 MuleSoft, Inc. This software is protected under international copyright
 * law. All use of this software is subject to MuleSoft's Master Subscription Agreement
 * (or other master license agreement) separately entered into in writing between you and
 * MuleSoft. If such an agreement is not in place, you may not use the software.
 */

package com.mulesoft.mule.example.security;

import org.mule.tck.FunctionalTestCase;

import com.mulesoft.mule.example.security.Greeter;
import com.mulesoft.mule.example.security.SecureClient;
import com.mulesoft.mule.example.security.WrongPasswordCallback;

import java.util.Map;

import javax.xml.ws.soap.SOAPFaultException;

public final class SecurityTestCase extends FunctionalTestCase
{
    @Override
    protected String getConfigResources()
    {
        return "mule-config.xml";
    }

    public void testUnsecure() throws Exception
    {
        Greeter service = SecureClient.createService("http://localhost:63081/services/unsecure?wsdl", null);
        String reply = service.greet("Mule");
        assertEquals("Hello Mule", reply);
    }

    public void testUsernameToken() throws Exception
    {
        Greeter service = SecureClient.createService("http://localhost:63081/services/username?wsdl",
            SecureClient.getUsernameTokenProps("UsernameToken Timestamp", "wssecurity.properties"));
        String reply = service.greet("Mule");
        assertEquals("Hello Mule", reply);
    }

    public void testUsernameTokenWrongPasswordError() throws Exception
    {
        Map wss4jProps = SecureClient.getUsernameTokenProps("UsernameToken Timestamp", "wssecurity.properties");
        wss4jProps.put("passwordCallbackClass", WrongPasswordCallback.class.getName());

        Greeter service = SecureClient.createService("http://localhost:63081/services/username?wsdl", wss4jProps);
        try
        {
            service.greet("Mule");
            fail("Request should have thrown a security exception");
        }
        catch (SOAPFaultException e)
        {
            assertTrue(e.getMessage().contains("security token"));
        }
    }

    public void testUsernameTokenSigned() throws Exception
    {
        Greeter service = SecureClient.createService("http://localhost:63081/services/signed?wsdl",
            SecureClient.getUsernameTokenProps("UsernameToken Signature Timestamp", "wssecurity.properties"));
        String reply = service.greet("Mule");
        assertEquals("Hello Mule", reply);
    }

    public void testUsernameTokenUnsignedError() throws Exception
    {
        Greeter service = SecureClient.createService("http://localhost:63081/services/signed?wsdl",
            SecureClient.getUsernameTokenProps("UsernameToken Timestamp", "wssecurity.properties"));
        try
        {
            service.greet("Mule");
            fail("Request should have thrown a security exception");
        }
        catch (SOAPFaultException e)
        {
            assertTrue(e.getMessage().contains("<wsse:Security> header"));
        }
    }

    public void testUsernameTokenEncrypted() throws Exception
    {
        Greeter service = SecureClient.createService("http://localhost:63081/services/encrypted?wsdl",
            SecureClient.getUsernameTokenProps("UsernameToken Timestamp Encrypt",
                "wssecurity.properties"));
        String reply = service.greet("Mule");
        assertEquals("Hello Mule", reply);
    }

    public void testSamlToken() throws Exception
    {
        Greeter service = SecureClient.createService("http://localhost:63081/services/saml?wsdl",
            SecureClient.getSamlTokenProps("SAMLTokenUnsigned Timestamp", "saml.properties"));
        String reply = service.greet("Mule");
        assertEquals("Hello Mule", reply);
    }
    
  public void testSamlTokenWrongSubject() throws Exception
  {
      Greeter service = SecureClient.createService("http://localhost:63081/services/saml?wsdl", 
          SecureClient.getSamlTokenProps("SAMLTokenUnsigned Timestamp", "wrong-saml.properties"));
      try
      {
          service.greet("Mule");
          fail("Request should have thrown a security exception");
      }
      catch (SOAPFaultException e)
      {
          assertTrue(e.getMessage().contains("SAML authorization"));
      }
  }
}
