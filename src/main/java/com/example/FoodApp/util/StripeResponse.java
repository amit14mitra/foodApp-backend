package com.example.FoodApp.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class StripeResponse {

    private String message;
    private String status;
    private String sessionId;
    private String sessionUrl;

    public StripeResponse(){

    }

    public StripeResponse(String message, String status, String sessionId, String sessionUrl) {
        this.message=message;
        this.status=status;
        this.sessionId=sessionId;
        this.sessionUrl=sessionUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionUrl() {
        return sessionUrl;
    }

    public void setSessionUrl(String sessionUrl) {
        this.sessionUrl = sessionUrl;
    }

    // Static builder method
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String message;
        private String status;
        private String sessionId;
        private String sessionUrl;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder sessionUrl(String sessionUrl) {
            this.sessionUrl = sessionUrl;
            return this;
        }

        public StripeResponse build() {
            return new StripeResponse(message, status, sessionId, sessionUrl);
        }
    }

}
