package com.woowa.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Question {
    private final String questionId;
    private String title;
    private String content;
    private final List<Reply> replies = new ArrayList<>();
    private final Author author;
    private final ZonedDateTime createdAt;
    private boolean deleted;

    private Question(String questionId, String title, String content, Author author, ZonedDateTime createdAt,
                     boolean deleted) {
        this.questionId = questionId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.deleted = deleted;
    }

    public static Question create(String questionId, String title, String content, Author author,
                                  ZonedDateTime createdBy) {
        return new Question(questionId, title, content, author, createdBy, false);
    }

    public static Question create(String questionId, String title, String content, Author author,
                                  ZonedDateTime createdBy, boolean deleted) {
        return new Question(questionId, title, content, author, createdBy, deleted);
    }

    public void checkAuthority(User user) {
        author.checkAuthority(user);
    }

    public void update(String title, String content) {
        if (title != null && !title.isBlank()) {
            this.title = title;
        }
        if (content != null && !content.isBlank()) {
            this.content = content;
        }
    }

    public void delete() {
        for (Reply reply : replies) {
            reply.checkAuthority(author);
        }
        for (Reply reply : replies) {
            reply.delete();
        }
        this.deleted = true;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public Author getAuthor() {
        return author;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId='" + questionId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", replies=" + replies +
                ", author=" + author +
                ", createdAt=" + createdAt +
                ", deleted=" + deleted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Question question = (Question) o;
        return deleted == question.deleted && Objects.equals(questionId, question.questionId)
                && Objects.equals(title, question.title) && Objects.equals(content, question.content)
                && Objects.equals(replies, question.replies) && Objects.equals(author, question.author)
                && Objects.equals(createdAt, question.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, title, content, replies, author, createdAt, deleted);
    }
}
