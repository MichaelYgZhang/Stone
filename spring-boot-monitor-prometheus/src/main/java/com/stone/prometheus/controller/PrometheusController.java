package com.stone.prometheus.controller;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping(value = "/prometheus")
public class PrometheusController {

    final Counter request_total = Counter.build().name("test_prometheus_request_total").labelNames("test_counter").help("Total number of requests.").register();
    final Gauge request_rand = Gauge.build().name("test_prometheus_request_rand").help("Rand number of request.").create().register();
    final Histogram requestLatency = Histogram.build().name("test_prometheus_requests_latency_seconds").help("Request latency in seconds.").register();

    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    public String testMonitor() {

        request_total.labels("test_monitor").inc();
        System.out.println(request_total.labels("test_monitor").get());
        Random random = new Random();
        request_rand.set(random.nextInt());

        // Start the histogram timer
        Histogram.Timer requestTimer = requestLatency.startTimer();
        try {
            System.out.println("test...histogram");
        } finally {
            // Stop the histogram timer
            requestTimer.observeDuration();
        }
        return "success";
    }
}
