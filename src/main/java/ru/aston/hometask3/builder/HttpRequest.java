package ru.aston.hometask3.builder;

import java.util.HashMap;
import java.util.Map;

public record HttpRequest(
        String url,
        String method,
        Map<String, String> headers,
        String body,
        int timeoutMs,
        boolean followRedirects
) {
    private HttpRequest(Builder builder) {
        this(
                builder.url,
                builder.method,
                Map.copyOf(builder.headers),
                builder.body,
                builder.timeoutMs,
                builder.followRedirects
        );
    }

    public static class Builder {
        private final String url;

        private String method = "GET";
        private Map<String, String> headers = new HashMap<>();
        private String body = null;
        private int timeoutMs = 5000;
        private boolean followRedirects = true;

        public Builder(String url) {
            if (url == null || url.isBlank()) {
                throw new IllegalArgumentException("URL не может быть пустым");
            }
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                throw new IllegalArgumentException("URL должен начинаться с http:// или https://");
            }
            this.url = url;
        }

        public Builder method(String method) {
            if (method == null || method.isBlank()) {
                throw new IllegalArgumentException("Метод не может быть пустым");
            }
            this.method = method.toUpperCase();
            return this;
        }

        public Builder header(String name, String value) {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Имя заголовка не может быть пустым");
            }
            this.headers.put(name, value);
            return this;
        }

        public Builder headers(Map<String, String> headers) {
            if (headers != null) {
                this.headers.putAll(headers);
            }
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder timeout(int timeoutMs) {
            if (timeoutMs <= 0) {
                throw new IllegalArgumentException("Timeout должен быть > 0");
            }
            this.timeoutMs = timeoutMs;
            return this;
        }

        public Builder followRedirects(boolean follow) {
            this.followRedirects = follow;
            return this;
        }

        public HttpRequest build() {
            if (("POST".equals(method) || "PUT".equals(method)) && body == null) {
                throw new IllegalStateException(
                        "Для метода " + method + " необходимо указать body"
                );
            }

            return new HttpRequest(this);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "HttpRequest{method='%s', url='%s', headers=%s, body='%s', timeout=%dms, redirects=%b}",
                method, url, headers, body != null ? body : "null", timeoutMs, followRedirects
        );
    }
}
