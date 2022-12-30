package com.kitex.kitex.exception.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
public class ErrorDto {
    public String title;
    public String detail;
    public Integer status;
    public String errorType;
    public String errorCode;
    public String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a z Z"));

    public  ErrorDto (String title, String detail, Integer status, String errorType, String errorCode) {
        this.title = title;
        this.detail = detail;
        this.status = status;
        this.errorType =errorType;
        this.errorCode = errorCode;

    }

    public static ErrorDTOBuilder builder() {
        return new ErrorDTOBuilder();
    }
    public static class ErrorDTOBuilder {
        public String title;
        public String detail;
        public Integer status;
        public String errorType;
        public String errorCode;


        public ErrorDTOBuilder withTitle(final String title) {
            this.title = title;
            return this;
        }

        public ErrorDTOBuilder withDetail(final String detail) {
            this.detail = detail;
            return this;
        }

        public ErrorDTOBuilder withStatus(final Integer status) {
            this.status = status;
            return this;
        }

        public ErrorDTOBuilder withErrorType(final String errorType) {
            this.errorType = errorType;
            return this;
        }

        public ErrorDTOBuilder withErrorCode(final String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ErrorDto build() {
            return new ErrorDto(title, detail, status,errorType, errorCode);
        }

    }
}
