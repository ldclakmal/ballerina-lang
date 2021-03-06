module io.ballerina.observability {
    requires gson;
    requires io.ballerina.lang;
    requires io.ballerina.runtime;
    requires io.ballerina.config;
    requires io.opentelemetry.api;
    requires io.opentelemetry.sdk.testing;
    requires io.opentelemetry.sdk.trace;
    requires io.opentelemetry.context;
    requires slf4j.api;

    provides io.ballerina.runtime.observability.tracer.spi.TracerProvider
            with org.ballerinalang.observe.mockextension.BMockTracerProvider;
}
