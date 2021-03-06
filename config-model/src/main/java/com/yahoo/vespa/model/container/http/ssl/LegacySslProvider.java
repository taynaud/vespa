// Copyright 2018 Yahoo Holdings. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
package com.yahoo.vespa.model.container.http.ssl;

import com.yahoo.component.ComponentId;
import com.yahoo.container.bundle.BundleInstantiationSpecification;
import com.yahoo.jdisc.http.ConnectorConfig;
import com.yahoo.jdisc.http.ssl.SslContextFactoryProvider;
import com.yahoo.jdisc.http.ssl.impl.LegacySslContextFactoryProvider;
import com.yahoo.osgi.provider.model.ComponentModel;
import com.yahoo.vespa.model.container.component.SimpleComponent;

import static com.yahoo.component.ComponentSpecification.fromString;

/**
 * Provides a legacy implementation of {@link SslContextFactoryProvider} to be injected into non-ssl connectors and connectors using legacy ssl config override
 *
 * @author bjorncs
 */
public class LegacySslProvider extends SimpleComponent implements ConnectorConfig.Producer {

    public static final String COMPONENT_ID_PREFIX = "legacy-ssl-provider@";
    public static final String COMPONENT_CLASS = LegacySslContextFactoryProvider.class.getName();
    public static final String COMPONENT_BUNDLE = "jdisc_http_service";

    public LegacySslProvider(String serverName) {
        super(new ComponentModel(
                new BundleInstantiationSpecification(new ComponentId(COMPONENT_ID_PREFIX + serverName),
                                                     fromString(COMPONENT_CLASS),
                                                     fromString(COMPONENT_BUNDLE))));
    }

    @Override
    public void getConfig(ConnectorConfig.Builder builder) {

    }
}
