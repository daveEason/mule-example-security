/*
 * $Id: VerifyAuthorization.java 18408 2010-12-09 19:44:58Z travis.carlson $
 * --------------------------------------------------------------------------------------
 *
 * (c) 2003-2010 MuleSoft, Inc. This software is protected under international copyright
 * law. All use of this software is subject to MuleSoft's Master Subscription Agreement
 * (or other master license agreement) separately entered into in writing between you and
 * MuleSoft. If such an agreement is not in place, you may not use the software.
 */

package com.mulesoft.mule.example.security;

import org.mule.api.security.SecurityException;
import org.mule.api.security.UnauthorisedException;
import org.mule.config.i18n.Message;
import org.mule.config.i18n.MessageFactory;

import com.mulesoft.mule.saml.SAMLAuthenticationAdapter;
import com.mulesoft.mule.saml.cxf.SAMLVerifyCallback;

import org.opensaml.SAMLSubject;

public class VerifyAuthorization implements SAMLVerifyCallback
{
    private String subject;
    
    public SAMLAuthenticationAdapter verify(SAMLAuthenticationAdapter samlAuthentication) throws SecurityException
    {
        SAMLSubject samlSubject = samlAuthentication.getSubject();
        if (!samlSubject.getNameIdentifier().getName().equals(subject))
        {
            Message message = MessageFactory.createStaticMessage("Missing SAML authorization for resource: " + subject);
            throw new UnauthorisedException(message, (Throwable) null);
        }
        return samlAuthentication;
    }
    
    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }
}


