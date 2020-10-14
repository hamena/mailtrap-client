package com.java7notes.mailtrap;

public class SearchCriteria {
    
    String subject, containsSubject, fromEmail, fromName, toEmail, toName;
    int isRead = -1;

    public SearchCriteria() {}

    public SearchCriteria withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public SearchCriteria containsSubject(String subject) {
        this.containsSubject = subject;
        return this;
    }

    public SearchCriteria withFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
        return this;
    }

    public SearchCriteria withFromName(String fromName) {
        this.fromName = fromName;
        return this;
    }

    public SearchCriteria withToEmail(String toEmail) {
        this.toEmail = toEmail;
        return this;
    }

    public SearchCriteria withToName(String toName) {
        this.toName = toName;
        return this;
    }

    public SearchCriteria isRead(boolean isRead) {
        this.isRead = isRead ? 1 : 0;
        return this;
    }

    public boolean evaluate(Message message) {
        boolean condition = true;
        if (condition && subject!=null) {
            condition = condition && message.getSubject().equals(subject);
        }
        if (condition && containsSubject!=null) {
            condition = condition && message.getSubject().contains(containsSubject);
        }
        if (condition && fromEmail!=null) {
            condition = condition && message.getFromEmail().equals(fromEmail);
        }
        if (condition && fromName!=null) {
            condition = condition && message.getFromName().equals(fromName);
        }
        if (condition && toEmail!=null) {
            condition = condition && message.getToEmail().equals(toEmail);
        }
        if (condition && toName!=null) {
            condition = condition && message.getToName().equals(toName);
        }
        if (condition && isRead>=0) {
            int intRead = message.isRead() ? 1 : 0;
            condition = condition && intRead == isRead;
        }
        return condition;
    }
}
