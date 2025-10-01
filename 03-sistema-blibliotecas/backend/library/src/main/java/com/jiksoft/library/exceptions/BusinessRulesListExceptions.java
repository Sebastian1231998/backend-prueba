package com.jiksoft.library.exceptions;

import java.io.Serial;

public class BusinessRulesListExceptions {

    private BusinessRulesListExceptions() {
    }

    public static class LibraryManagementServiceException extends BaseException {

        @Serial
        private static final long serialVersionUID = 1L;

        public LibraryManagementServiceException(String msg, Throwable err) {
            super(msg, err);
        }

        public LibraryManagementServiceException(String msg) {
            super(msg);
        }
    }

    public static class LoanException extends BaseException {

        @Serial
        private static final long serialVersionUID = 1L;

        public LoanException(String msg, Throwable err) {
            super(msg, err);
        }

        public LoanException(String msg) {
            super(msg);
        }
    }

    public static class MembershipException extends BaseException {

        @Serial
        private static final long serialVersionUID = 1L;

        public MembershipException(String msg, Throwable err) {
            super(msg, err);
        }

        public MembershipException(String msg) {
            super(msg);
        }
    }

}

