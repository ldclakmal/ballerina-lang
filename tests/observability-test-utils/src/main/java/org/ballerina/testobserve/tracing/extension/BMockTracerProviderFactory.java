/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ballerina.testobserve.tracing.extension;

import io.ballerina.runtime.api.creators.ValueCreator;
import io.ballerina.runtime.api.values.BObject;
import io.ballerina.runtime.observability.tracer.spi.TracerProvider;
import io.ballerina.runtime.observability.tracer.spi.TracerProviderFactory;
import io.opentracing.Tracer;
import io.opentracing.mock.MockTracer;

import java.io.PrintStream;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.ballerina.testobserve.Constants.OBSERVE_MODULE;

/**
 * Tracer provider factory which returns mock tracer provider instance.
 */
public class BMockTracerProviderFactory implements TracerProviderFactory {
    private static final BObject BOBJECT_INSTANCE =
            ValueCreator.createObjectValue(OBSERVE_MODULE, "MockTracerProvider");
    private static final TracerProvider TRACER_PROVIDER_INSTANCE = new BMockTracerProvider();

    @Override
    public String getName() {
        return "mock";
    }

    @Override
    public BObject getProviderBObject() {
        return BOBJECT_INSTANCE;
    }

    @Override
    public TracerProvider getProvider() {
        return TRACER_PROVIDER_INSTANCE;
    }

    /**
     * Tracer provider which returns mock tracer instance.
     */
    public static class BMockTracerProvider implements TracerProvider {

        private static final PrintStream out = System.out;
        private static final Map<String, MockTracer> tracerMap = new ConcurrentHashMap<>();

        public static Map<String, MockTracer> getTracerMap() {
            return Collections.unmodifiableMap(tracerMap);
        }

        @Override
        public Tracer getTracer(String serviceName) {
            MockTracer mockTracer = new MockTracer();
            tracerMap.put(serviceName, mockTracer);
            out.println("Initialized Mock Tracer for " + serviceName);
            return mockTracer;
        }
    }
}
