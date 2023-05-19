package com.randazzo.tep.payload;

import jakarta.validation.constraints.NotNull;

public class UpdateRequestBody {
    @NotNull(message = "Please enter accepted value")
    private Boolean accepted;

    @NotNull(message = "Please enter request id")
    Long requestId;

    @NotNull(message = "Please enter request id")
    Long userId;

    public UpdateRequestBody() {}

    public UpdateRequestBody(Boolean accepted, Long requestId, Long userId) {
        this.accepted = accepted;
        this.requestId = requestId;
        this.userId = userId;
    }

    public Long getRequestId() {
        return this.requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean isAccepted() {
        return this.accepted;
    }

    public Boolean getAccepted() {
        return this.accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
