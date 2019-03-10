# Metrics for Eclipse MicroProfile

To ensure reliable operation of software it is necessary to monitor essential system parameters.

## Metrics Setup

Metrics along with respective metric-metadata is exposed using Microprofile Metrics, these metrics can either be queried  
as JSON or as a Prometheus "friendly" output. Metrics are divided into 3 categories:

1. **base**: metrics that all MicroProfile vendors have to provide
   - exposed under `/metrics/base`
2. **vendor**: vendor specific metrics (optional)
   - exposed under `/metrics/vendor`
3. **application**: application-specific metrics (optional)
   - exposed under `/metrics/application`

## Application Tagging

Tags (labels) play an important role in monitoring. They may be used to identify an application, e.g. `app=myShop`  
and is used in monitoring tools (e.g. Prometheus) to filter and group metric-data of distributed applications.

Tags can be applied in two ways:

1. At the metric-level .........
   - ``
2. At the application level using `MP_METRICS_TAGS` environment variable
   - e.g `MP_METRICS_TAGS=app=myShop,tier=integration.....`

## Metric types

1.  `counter`: an incrementally increasing or decreasing numeric value (e.g. total number of
    requests received or total number of concurrently active HTTP sessions).
2.  `gauge`: a metric that is sampled to obtain its value (e.g. cpu temperature or disk usage).
3.  `meter`: a metric which tracks mean throughput and one-, five-, and fifteen-minute
    exponentially-weighted moving average throughput.
4.  `histogram`: a metric which calculates the distribution of a value.
5.  `timer`: a metric which aggregates timing durations and provides duration statistics, plus
    throughput statistics.

