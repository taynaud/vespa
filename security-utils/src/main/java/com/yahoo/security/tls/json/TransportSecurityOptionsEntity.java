// Copyright 2018 Yahoo Holdings. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
package com.yahoo.security.tls.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Jackson bindings for transport security options
 *
 * @author bjorncs
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class TransportSecurityOptionsEntity {

    @JsonProperty("files") Files files;
    @JsonProperty("authorized-peers") List<AuthorizedPeer> authorizedPeers = new ArrayList<>();

    static class Files {
        @JsonProperty("private-key") String privateKeyFile;
        @JsonProperty("certificates") String certificatesFile;
        @JsonProperty("ca-certificates") String caCertificatesFile;
    }

    static class AuthorizedPeer {
        @JsonProperty("required-credentials") List<RequiredCredential> requiredCredentials = new ArrayList<>();
        @JsonProperty("name") String name;
    }

    static class RequiredCredential {
        @JsonProperty("field") CredentialField field;
        @JsonProperty("must-match") String matchExpression;
    }

    enum CredentialField { CN, SAN_DNS }
}
